package com.mentalmachines.han.data.repository

import androidx.room.Dao
import androidx.room.Query
import com.mentalmachines.han.data.model.Hanja

/**
 * The Data Access Object for the [HanjaDao] class.
 */
@Dao
interface HanjaDao {
        @Query("SELECT * FROM hanja")
        fun getHanja(): List<Hanja>
}