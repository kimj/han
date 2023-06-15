package com.mentalmachines.han.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mentalmachines.han.data.model.Grammar
import com.mentalmachines.han.data.model.Hanja
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Type
import java.util.Date


/**
 * The Room database for this app
 */
@Database(entities = [Hanja::class, Grammar::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HanDatabase : RoomDatabase() {
    abstract fun hanjaDao(): HanjaDao
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun flashCardDao(): FlashCardDao
    abstract fun grammarDao(): GrammarDao


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: HanDatabase? = null

        fun getInstance(context: Context): HanDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): HanDatabase {
            val DATABASE_NAME = "sunflower-db"
            val KEY_FILENAME = "plants.json"

            return Room.databaseBuilder(
                context,
                HanDatabase::class.java, DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ArrayList<String>> = moshi.adapter<ArrayList<String>>()


        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
        return jsonAdapter.fromJson(json)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ArrayList<String?>> = moshi.adapter<ArrayList<String?>>()

        return jsonAdapter.toJson(list)
    }
}


private val DB_PATH = "/data/data/**YOUR_PACKAGE_NAME**/databases/"
private val DB_NAME = "**YOUR_DB_NAME**.db"

private fun copyDataBaseFromAssets(context: Context) {

    var myInput: InputStream? = null
    var myOutput: OutputStream? = null
    try {

        val folder = context.getDatabasePath("databases")

        if (!folder.exists())
            if (folder.mkdirs()) folder.delete()

        myInput = context.assets.open("databases/$DB_NAME")

        val outFileName = DB_PATH + DB_NAME

        val f = File(outFileName)

        if (f.exists())
            return

        myOutput = FileOutputStream(outFileName)

        //transfer bytes from the inputfile to the outputfile
        val buffer = ByteArray(1024)
        var length: Int = myInput.read(buffer)

        while (length > 0) {
            myOutput.write(buffer, 0, length)
            length = myInput.read(buffer)
        }

        //Close the streams
        myOutput.flush()
        myOutput.close()
        myInput.close()


    } catch (e: IOException) {
        e.printStackTrace()
    }

}
