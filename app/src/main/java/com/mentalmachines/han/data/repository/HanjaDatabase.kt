package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Hanja::class], version = 1)
abstract class HanjaDatabase : RoomDatabase() {
    abstract fun hanjaDao(): HanjaDao
    abstract fun flashCardDao(): FlashCardDao

    abstract fun grammarDao(): GrammarDao

    abstract fun dictionaryDao(): DictionaryDao


}