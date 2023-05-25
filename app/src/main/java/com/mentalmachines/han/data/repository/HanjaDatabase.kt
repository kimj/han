package com.mentalmachines.han.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [HanjaContent::class], version = 1)
abstract class HanjaDatabase : RoomDatabase()