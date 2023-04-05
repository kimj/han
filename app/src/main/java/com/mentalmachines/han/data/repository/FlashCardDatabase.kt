package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Hanja::class], version = 1)
abstract class FlashCardDatabase : RoomDatabase() {
    abstract fun flashCardDao(): FlashCardDao
}