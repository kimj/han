package com.mentalmachines.han.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [HanjaDao] class.
 */
@Dao
interface HanjaDao {
    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): Flow<List<HanjaDao>>

    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    fun getHanja(): Flow<List<Hanja>>

    @Insert
    suspend fun insertHanja(hanja: Hanja): Long

    @Delete
    suspend fun deleteHanja(hanja: Hanja)
}