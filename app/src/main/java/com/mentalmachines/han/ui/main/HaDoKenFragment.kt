package com.mentalmachines.han.ui.main

import android.net.ParseException
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ApiException
import com.mentalmachines.han.R
import com.mentalmachines.han.SimpleWikiHelper

class HaDoKenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // String sResult = new String();
        val searchButton = findViewById(R.id.search) as Button
        searchButton.setOnClickListener {
            // grab @+id/searchString and send this to the xml request handler
            val searchText: EditText = findViewById(R.id.searchText) as EditText
            val resultsText: TextView = findViewById(R.id.resultsText) as TextView
            var pageContent: String? = null

            // get a ParsedPage object
            val pf = MediaWikiParserFactory()
            val parser: MediaWikiParser = pf.createParser()
            val pp: ParsedPage = parser.parse(pageContent)

            // only the links to other Wikipedia language editions
            for (language in pp.getLanguages()) {
                System.out.println(language.getTarget())
            }

            //get the internal links of each section
            for (section in pp.getSections()) {
                System.out.println("Section: " + section.getTitle())
                for (link in section.getLinks(Link.type.INTERNAL)) {
                    System.out.println("  " + link.getTarget())
                }
            }
            try {
                SimpleWikiHelper.prepareUserAgent(getApplicationContext())
                pageContent = SimpleWikiHelper.getPageContent("" + searchText.text, false)
                Log.w("HadoKen", pageContent)
            } catch (e: ApiException) {
                Log.e("WordWidget", "Couldn't contact API", e)
            } catch (e: ParseException) {
                Log.e("WordWidget", "Couldn't parse API response", e)
            }
            resultsText.text = pageContent
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Composable
    private fun
}