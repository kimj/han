package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mentalmachines.han.data.model.Grammar


@Database(entities = [Grammar::class], version = 1)
abstract class GrammarDatabase : RoomDatabase() {
    abstract fun hanjaDao(): HanjaDao
}