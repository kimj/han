package com.mentalmachines.han

/*
 * Copyright (C) 2011 readyState Software Ltd, 2007 The Android Open Source Project
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

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.mentalmachines.han.SQLiteAssetHelper.SQLiteAssetException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.Collections
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.zip.ZipInputStream


/**
 * A helper class to manage database creation and version management using
 * an application's raw asset files.
 *
 * This class provides developers with a simple way to ship their Android app
 * with an existing SQLite database (which may be pre-populated with data) and
 * to manage its initial creation and any upgrades required with subsequent
 * version releases.
 *
 *
 * This class makes it easy for [android.content.ContentProvider]
 * implementations to defer opening and upgrading the database until first use,
 * to avoid blocking application startup with long-running database upgrades.
 *
 *
 * For examples see [
 * https://github.com/jgilfelt/android-sqlite-asset-helper](https://github.com/jgilfelt/android-sqlite-asset-helper)
 *
 *
 * **Note:** this class assumes
 * monotonically increasing version numbers for upgrades.  Also, there
 * is no concept of a database downgrade; installing a new version of
 * your app which uses a lower version number than a
 * previously-installed version will result in undefined behavior.
 */
class SQLiteAssetHelper(
    context: Context,
    name: String?,
    storageDirectory: String?,
    factory: CursorFactory,
    version: Int,
    errorHandler: DatabaseErrorHandler?
) :
    SQLiteOpenHelper(context, name, factory, version, errorHandler) {
    private val mContext: Context
    private val mName: String?
    private val mFactory: CursorFactory
    private val mNewVersion: Int
    private var mDatabase: SQLiteDatabase? = null
    private var mIsInitializing = false
    private var mDatabasePath: String? = null
    private val mAssetPath: String
    private val mUpgradePathFormat: String
    private var mForcedUpgradeVersion = 0

    /**
     * Create a helper object to create, open, and/or manage a database in
     * a specified location.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of [.getWritableDatabase] or
     * [.getReadableDatabase] is called.
     *
     * @param context to use to open or create the database
     * @param name of the database file
     * @param storageDirectory to store the database file upon creation; caller must
     * ensure that the specified absolute path is available and can be written to
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     * SQL file(s) contained within the application assets folder will be used to
     * upgrade the database
     */
    init {
        if (version < 1) throw IllegalArgumentException("Version must be >= 1, was $version")
        if (name == null) throw IllegalArgumentException("Database name cannot be null")
        mContext = context
        mName = name
        mFactory = factory
        mNewVersion = version
        mAssetPath = ASSET_DB_PATH + "/" + name
        if (storageDirectory != null) {
            mDatabasePath = storageDirectory
        } else {
            mDatabasePath = context.applicationInfo.dataDir + "/databases"
        }
        mUpgradePathFormat = ASSET_DB_PATH + "/" + name + "_upgrade_%s-%s.sql"
    }

    /**
     * Create a helper object to create, open, and/or manage a database in
     * the application's default private data directory.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of [.getWritableDatabase] or
     * [.getReadableDatabase] is called.
     *
     * @param context to use to open or create the database
     * @param name of the database file
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     * SQL file(s) contained within the application assets folder will be used to
     * upgrade the database
     */
    constructor(context: Context, name: String?, factory: CursorFactory, version: Int) : this(
        context,
        name,
        null,
        factory,
        version,
        null
    )

    /**
     * Create and/or open a database that will be used for reading and writing.
     * The first time this is called, the database will be extracted and copied
     * from the application's assets folder.
     *
     *
     * Once opened successfully, the database is cached, so you can
     * call this method every time you need to write to the database.
     * (Make sure to call [.close] when you no longer need the database.)
     * Errors such as bad permissions or a full disk may cause this method
     * to fail, but future attempts may succeed if the problem is fixed.
     *
     *
     * Database upgrade may take a long time, you
     * should not call this method from the application main thread, including
     * from [ContentProvider.onCreate()][android.content.ContentProvider.onCreate].
     *
     * @return a read/write database object valid until [.close] is called
     * @throws SQLiteException if the database cannot be opened for writing
     */
    @Synchronized
    override fun getWritableDatabase(): SQLiteDatabase {
        if (mDatabase != null && mDatabase!!.isOpen && !mDatabase!!.isReadOnly) {
            return mDatabase as SQLiteDatabase // The database is already open for business
        }
        if (mIsInitializing) {
            throw IllegalStateException("getWritableDatabase called recursively")
        }

        // If we have a read-only database open, someone could be using it
        // (though they shouldn't), which would cause a lock to be held on
        // the file, and our attempts to open the database read-write would
        // fail waiting for the file lock.  To prevent that, we acquire the
        // lock on the read-only database, which shuts out other users.
        var success = false
        var db: SQLiteDatabase? = null
        //if (mDatabase != null) mDatabase.lock();
        try {
            mIsInitializing = true
            //if (mName == null) {
            //    db = SQLiteDatabase.create(null);
            //} else {
            //    db = mContext.openOrCreateDatabase(mName, 0, mFactory);
            //}
            db = createOrOpenDatabase(false)
            var version = db!!.version

            // do force upgrade
            if (version != 0 && version < mForcedUpgradeVersion) {
                db = createOrOpenDatabase(true)
                db!!.version = mNewVersion
                version = db.version
            }
            if (version != mNewVersion) {
                db.beginTransaction()
                try {
                    if (version == 0) {
                        onCreate(db)
                    } else {
                        if (version > mNewVersion) {
                            Log.w(
                                TAG, "Can't downgrade read-only database from version " +
                                        version + " to " + mNewVersion + ": " + db.path
                            )
                        }
                        onUpgrade(db, version, mNewVersion)
                    }
                    db.version = mNewVersion
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            }
            onOpen(db)
            success = true
            return db
        } finally {
            mIsInitializing = false
            if (success) {
                if (mDatabase != null) {
                    try {
                        mDatabase!!.close()
                    } catch (e: Exception) {
                    }
                    //mDatabase.unlock();
                }
                mDatabase = db
            } else {
                //if (mDatabase != null) mDatabase.unlock();
                db?.close()
            }
        }
    }

    /**
     * Create and/or open a database.  This will be the same object returned by
     * [.getWritableDatabase] unless some problem, such as a full disk,
     * requires the database to be opened read-only.  In that case, a read-only
     * database object will be returned.  If the problem is fixed, a future call
     * to [.getWritableDatabase] may succeed, in which case the read-only
     * database object will be closed and the read/write object will be returned
     * in the future.
     *
     *
     * Like [.getWritableDatabase], this method may
     * take a long time to return, so you should not call it from the
     * application main thread, including from
     * [ContentProvider.onCreate()][android.content.ContentProvider.onCreate].
     *
     * @return a database object valid until [.getWritableDatabase]
     * or [.close] is called.
     * @throws SQLiteException if the database cannot be opened
     */
    @Synchronized
    override fun getReadableDatabase(): SQLiteDatabase {
        if (mDatabase != null && mDatabase!!.isOpen) {
            return mDatabase as SQLiteDatabase // The database is already open for business
        }
        if (mIsInitializing) {
            throw IllegalStateException("getReadableDatabase called recursively")
        }
        try {
            return writableDatabase
        } catch (e: SQLiteException) {
            if (mName == null) throw e // Can't open a temp database read-only!
            Log.e(
                TAG,
                "Couldn't open $mName for writing (will try read-only):", e
            )
        }
        var db: SQLiteDatabase? = null
        try {
            mIsInitializing = true
            val path = mContext.getDatabasePath(mName).path
            db = SQLiteDatabase.openDatabase(path, mFactory, SQLiteDatabase.OPEN_READONLY)
            if (db.getVersion() != mNewVersion) {
                throw SQLiteException(
                    "Can't upgrade read-only database from version " +
                            db.getVersion() + " to " + mNewVersion + ": " + path
                )
            }
            onOpen(db)
            Log.w(TAG, "Opened $mName in read-only mode")
            mDatabase = db
            return mDatabase as SQLiteDatabase
        } finally {
            mIsInitializing = false
            if (db != null && db != mDatabase) db.close()
        }
    }

    /**
     * Close any open database object.
     */
    @Synchronized
    override fun close() {
        if (mIsInitializing) throw IllegalStateException("Closed during initialization")
        if (mDatabase != null && mDatabase!!.isOpen) {
            mDatabase!!.close()
            mDatabase = null
        }
    }

    override fun onConfigure(db: SQLiteDatabase) {
        // not supported!
    }

    override fun onCreate(db: SQLiteDatabase) {
        // do nothing - createOrOpenDatabase() is called in
        // getWritableDatabase() to handle database creation.
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(
            TAG,
            "Upgrading database $mName from version $oldVersion to $newVersion..."
        )
        val paths = ArrayList<String>()
        getUpgradeFilePaths(oldVersion, newVersion - 1, newVersion, paths)
        if (paths.isEmpty()) {
            Log.e(
                TAG,
                "no upgrade script path from $oldVersion to $newVersion"
            )
            throw SQLiteAssetException(
                "no upgrade script path from $oldVersion to $newVersion"
            )
        }
        Collections.sort<String>(paths, VersionComparator())
        for (path: String in paths) {
            try {
                Log.w(TAG, "processing upgrade: $path")
                val `is` = mContext.assets.open(path)
                val sql: String =
                    com.jaus.albertogiunta.justintrain_oraritreni.db.sqliteAsset.Utils.convertStreamToString(
                        `is`
                    )
                if (sql != null) {
                    val cmds: List<String> =
                        com.jaus.albertogiunta.justintrain_oraritreni.db.sqliteAsset.Utils.splitSqlScript(
                            sql,
                            ';'
                        )
                    for (cmd: String in cmds) {
                        if (cmd.trim { it <= ' ' }.length > 0) {
                            db.execSQL(cmd)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        Log.w(
            TAG,
            "Successfully upgraded database $mName from version $oldVersion to $newVersion"
        )
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // not supported!
    }

    /**
     * Bypass the upgrade process (for each increment up to a given version) and simply
     * overwrite the existing database with the supplied asset file.
     *
     * @param version bypass upgrade up to this version number - should never be greater than the
     * latest database version.
     */
    @Deprecated("use {@link #setForcedUpgrade} instead.")
    fun setForcedUpgradeVersion(version: Int) {
        setForcedUpgrade(version)
    }

    /**
     * Bypass the upgrade process (for each increment up to a given version) and simply
     * overwrite the existing database with the supplied asset file.
     *
     * @param version bypass upgrade up to this version number - should never be greater than the
     * latest database version.
     */
    fun setForcedUpgrade(version: Int) {
        mForcedUpgradeVersion = version
    }

    /**
     * Bypass the upgrade process for every version increment and simply overwrite the existing
     * database with the supplied asset file.
     */
    fun setForcedUpgrade() {
        setForcedUpgrade(mNewVersion)
    }

    @Throws(SQLiteAssetException::class)
    private fun createOrOpenDatabase(force: Boolean): SQLiteDatabase? {

        // test for the existence of the db file first and don't attempt open
        // to prevent the error trace in log on API 14+
        var db: SQLiteDatabase? = null
        val file = File("$mDatabasePath/$mName")
        if (file.exists()) {
            db = returnDatabase()
        }
        //SQLiteDatabase db = returnDatabase();
        if (db != null) {
            // database already exists
            if (force) {
                Log.w(TAG, "forcing database upgrade!")
                copyDatabaseFromAssets()
                db = returnDatabase()
            }
            return db
        } else {
            // database does not exist, copy it from assets and return it
            copyDatabaseFromAssets()
            db = returnDatabase()
            return db
        }
    }

    private fun returnDatabase(): SQLiteDatabase? {
        try {
            val db = SQLiteDatabase.openDatabase(
                "$mDatabasePath/$mName",
                mFactory,
                SQLiteDatabase.OPEN_READWRITE
            )
            Log.i(
                TAG,
                "successfully opened database $mName"
            )
            return db
        } catch (e: SQLiteException) {
            Log.w(TAG, "could not open database " + mName + " - " + e.message)
            return null
        }
    }

    @Throws(SQLiteAssetException::class)
    private fun copyDatabaseFromAssets() {
        Log.w(TAG, "copying database from assets...")
        val path = mAssetPath
        val dest = "$mDatabasePath/$mName"
        var `is`: InputStream
        var isZip = false
        try {
            // try uncompressed
            `is` = mContext.assets.open(path)
        } catch (e: IOException) {
            // try zip
            try {
                `is` = mContext.assets.open("$path.zip")
                isZip = true
            } catch (e2: IOException) {
                // try gzip
                try {
                    `is` = mContext.assets.open("$path.gz")
                } catch (e3: IOException) {
                    val se = SQLiteAssetException(
                        "Missing $mAssetPath file (or .zip, .gz archive) in assets, or target folder not writable"
                    )
                    se.stackTrace = e3.stackTrace
                    throw se
                }
            }
        }
        try {
            val f = File("$mDatabasePath/")
            if (!f.exists()) {
                f.mkdir()
            }
            if (isZip) {
                val zis: ZipInputStream =
                    com.jaus.albertogiunta.justintrain_oraritreni.db.sqliteAsset.Utils.getFileFromZip(
                        `is`
                    )
                        ?: throw SQLiteAssetException("Archive is missing a SQLite database file")
                com.jaus.albertogiunta.justintrain_oraritreni.db.sqliteAsset.Utils.writeExtractedFileToDisk(
                    zis,
                    FileOutputStream(dest)
                )
            } else {
                com.jaus.albertogiunta.justintrain_oraritreni.db.sqliteAsset.Utils.writeExtractedFileToDisk(
                    `is`,
                    FileOutputStream(dest)
                )
            }
            Log.w(TAG, "database copy complete")
        } catch (e: IOException) {
            val se = SQLiteAssetException(
                "Unable to write $dest to data directory"
            )
            se.stackTrace = e.stackTrace
            throw se
        }
    }

    private fun getUpgradeSQLStream(oldVersion: Int, newVersion: Int): InputStream? {
        val path = String.format(mUpgradePathFormat, oldVersion, newVersion)
        return try {
            mContext.assets.open(path)
        } catch (e: IOException) {
            Log.w(
                TAG,
                "missing database upgrade script: $path"
            )
            null
        }
    }

    private fun getUpgradeFilePaths(
        baseVersion: Int,
        start: Int,
        end: Int,
        paths: ArrayList<String>
    ) {
        val a: Int
        val b: Int
        val inputStream = getUpgradeSQLStream(start, end)
        if (inputStream != null) {
            val path = String.format(mUpgradePathFormat, start, end)
            paths.add(path)
            a = start - 1
            b = start
        } else {
            a = start - 1
            b = end
        }
        if (a < baseVersion) {
            return
        } else {
            getUpgradeFilePaths(baseVersion, a, b, paths) // recursive call
        }
    }

    /**
     * An exception that indicates there was an error with SQLite asset retrieval or parsing.
     */
    class SQLiteAssetException : SQLiteException {
        constructor()
        constructor(error: String?) : super(error)
    }

    companion object {
        private val TAG = SQLiteAssetHelper::class.java.simpleName
        private val ASSET_DB_PATH = "databases"
    }
}

internal abstract class VersionComparator : Comparator<String?> {
    private val pattern: Pattern = Pattern
        .compile(".*_upgrade_([0-9]+)-([0-9]+).*")

    /**
     * Compares the two specified upgrade script strings to determine their
     * relative ordering considering their two version numbers. Assumes all
     * database names used are the same, as this function only compares the
     * two version numbers.
     *
     * @param file0 an upgrade script file name
     * @param file1 a second upgrade script file name to compare with file0
     * @return an integer < 0 if file0 should be applied before file1, 0 if
     * they are equal (though that shouldn't happen), and > 0 if
     * file0 should be applied after file1.
     * @throws SQLiteAssetException thrown if the strings are not in the correct upgrade
     * script format of:
     * `databasename_fromVersionInteger_toVersionInteger`
     */
    fun compare(file0: String, file1: String): Int {
        val m0: Matcher = pattern.matcher(file0)
        val m1: Matcher = pattern.matcher(file1)
        if (!m0.matches()) {
            Log.w(
                TAG,
                "could not parse upgrade script file: $file0"
            )
            throw SQLiteAssetException("Invalid upgrade script file")
        }
        if (!m1.matches()) {
            Log.w(
                TAG,
                "could not parse upgrade script file: $file1"
            )
            throw SQLiteAssetException("Invalid upgrade script file")
        }
        val v0_from = Integer.valueOf(m0.group(1))
        val v1_from = Integer.valueOf(m1.group(1))
        val v0_to = Integer.valueOf(m0.group(2))
        val v1_to = Integer.valueOf(m1.group(2))
        if (v0_from == v1_from) {
            // 'from' versions match for both; check 'to' version next
            if (v0_to == v1_to) {
                return 0
            }
            return if (v0_to < v1_to) -1 else 1
        }
        return if (v0_from < v1_from) -1 else 1
    }

    companion object {
        private val TAG = SQLiteAssetHelper::class.java.simpleName
    }
}