package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mentalmachines.han.data.model.Hanja

@Database(entities = [Hanja::class], version = 1)
abstract class HanjaDatabase : RoomDatabase()