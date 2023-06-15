package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mentalmachines.han.data.model.Dictionary


@Database(entities = [Dictionary::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun flashCardDao(): FlashCardDao
}