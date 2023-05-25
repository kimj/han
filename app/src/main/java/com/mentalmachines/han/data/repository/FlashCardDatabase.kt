package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mentalmachines.han.data.model.Flashcard


@Database(entities = [Flashcard::class], version = 1)
abstract class FlashCardDatabase : RoomDatabase() {
    abstract fun flashCardDao(): FlashCardDao
}