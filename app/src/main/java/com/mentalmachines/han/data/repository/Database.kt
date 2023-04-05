package com.mentalmachines.han.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mentalmachines.han.data.model.Grammar

/**
 * The Room database for this app
 */
@Database(entities = [Hanja::class, Grammar::class], version = 1, exportSchema = false)
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
                HanDatabase::class.java, "database-name"
            ).build()
        }
    }
}