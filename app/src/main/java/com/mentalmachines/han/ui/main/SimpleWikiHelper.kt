/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mentalmachines.cjkdroid

import android.content.Context
import android.net.Uri
import android.util.Log
import org.apache.http.HttpEntity
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * Helper methods to simplify talking with and parsing responses from a
 * lightweight Wiktionary API. Before making any requests, you should call
 * [.prepareUserAgent] to generate a User-Agent string based on
 * your application package name and version.
 */
object SimpleWikiHelper {
    private const val TAG = "SimpleWikiHelper"

    /**
     * Partial URL to use when requesting the detailed entry for a specific
     * Wiktionary page. Use [String.format] to insert
     * the desired page title after escaping it as needed.
     */
    private const val WIKTIONARY_PAGE =
    //            "http://en.wiktionary.org/w/api.php?action=query&prop=revisions&titles=%s&" +
        //            "rvprop=content&format=json%s";
        "http://en.wiktionary.org/w/api.php?action=query&prop=revisions&rvprop=content&format=json&titles="

    /**
     * Partial URL to append to [.WIKTIONARY_PAGE] when you want to expand
     * any templates found on the requested page. This is useful when browsing
     * full entries, but may use more network bandwidth.
     */
    private const val WIKTIONARY_EXPAND_TEMPLATES = "&rvexpandtemplates=true"

    /**
     * [StatusLine] HTTP status code when no server error has occurred.
     */
    private const val HTTP_STATUS_OK = 200

    /**
     * Shared buffer used by [.getUrlContent] when reading results
     * from an API request.
     */
    private val sBuffer = ByteArray(512)

    /**
     * User-agent string to use when making requests. Should be filled using
     * [.prepareUserAgent] before making any other calls.
     */
    private var sUserAgent: String? = null

    /**
     * Prepare the internal User-Agent string for use. This requires a
     * [Context] to pull the package name and version number for this
     * application.
     */
    fun prepareUserAgent(context: Context) {
        try {
            // Read package name and version number from manifest
            val manager: PackageManager = context.packageManager
            val info: PackageInfo = manager.getPackageInfo(context.packageName, 0)
            sUserAgent = String.format(
                context.getString(R.string.template_user_agent),
                info.packageName, info.versionName
            )
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Couldn't find package information in PackageManager", e)
        }
    }

    /**
     * Read and return the content for a specific Wiktionary page. This makes a
     * lightweight API call, and trims out just the page content returned.
     * Because this call blocks until results are available, it should not be
     * run from a UI thread.
     *
     * @param title The exact title of the Wiktionary page requested.
     * @param expandTemplates If true, expand any wiki templates found.
     * @return Exact content of page.
     * @throws ApiException If any connection or server error occurs.
     * @throws ParseException If there are problems parsing the response.
     */
    @Throws(ApiException::class, ParseException::class)
    fun getPageContent(title: String, expandTemplates: Boolean): String {
        // Encode page title and expand templates if requested
        val encodedTitle = Uri.encode(title)
        val expandClause = if (expandTemplates) WIKTIONARY_EXPAND_TEMPLATES else ""

        // Query the API for content
        val content = getUrlContent(
            String.format(
                WIKTIONARY_PAGE + title,
                encodedTitle, expandClause
            )
        )
        return try {
            // Drill into the JSON response to find the content body
            val response = JSONObject(content)
            val query: JSONObject = response.getJSONObject("query")
            val pages: JSONObject = query.getJSONObject("pages")
            val page: JSONObject = pages.getJSONObject(pages.keys().next() as String)
            val revisions: JSONArray = page.getJSONArray("revisions")
            val revision: JSONObject = revisions.getJSONObject(0)
            revision.getString("*")
        } catch (e: JSONException) {
            throw ParseException("Problem parsing API response", e)
        }
    }

    /**
     * Pull the raw text content of the given URL. This call blocks until the
     * operation has completed, and is synchronized because it uses a shared
     * buffer [.sBuffer].
     *
     * @param url The exact URL to request.
     * @return The raw content returned by the server.
     * @throws ApiException If any connection or server error occurs.
     */
    @Synchronized
    @Throws(ApiException::class)
    internal fun getUrlContent(url: String?): String {
        if (sUserAgent == null) {
            throw ApiException("User-Agent string must be prepared")
        }

        // Create client and set our specific user-agent string
        val client: HttpClient = DefaultHttpClient()
        val request = HttpGet(url)
        request.setHeader("User-Agent", sUserAgent)
        return try {
            val response: HttpResponse = client.execute(request)

            // Check if server response is valid
            val status: StatusLine = response.getStatusLine()
            if (status.getStatusCode() !== HTTP_STATUS_OK) {
                throw ApiException(
                    "Invalid response from server: " +
                            status.toString()
                )
            }

            // Pull content stream from response
            val entity: HttpEntity = response.getEntity()
            val inputStream: InputStream = entity.getContent()
            val content = ByteArrayOutputStream()

            // Read response into a buffered stream
            var readBytes = 0
            while (inputStream.read(sBuffer).also { readBytes = it } != -1) {
                content.write(sBuffer, 0, readBytes)
            }

            // Return result from buffered stream
            String(content.toByteArray())
        } catch (e: IOException) {
            throw ApiException("Problem communicating with API", e)
        }
    }

    /**
     * Thrown when there were problems contacting the remote API server, either
     * because of a network error, or the server returned a bad status code.
     */
    class ApiException : Exception {
        constructor(detailMessage: String?, throwable: Throwable?) : super(
            detailMessage,
            throwable
        )

        constructor(detailMessage: String?) : super(detailMessage)
    }

    /**
     * Thrown when there were problems parsing the response to an API call,
     * either because the response was empty, or it was malformed.
     */
    class ParseException(detailMessage: String?, throwable: Throwable?) :
        Exception(detailMessage, throwable)
}