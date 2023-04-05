package com.mentalmachines.han.data.repository

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [FlashCardDao] class.
 */
@Dao
interface FlashCardDao {
    @Query("SELECT * FROM hanja")
    suspend fun getFlashCardList(): Flow<List<FlashCardDao>>

    @Query("SELECT * FROM hanja WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    suspend fun getDeck(): Flow<List<Hanja>>

    /*@Insert
    suspend fun insertHanja(hanja: Hanja): Long

    @Delete
    fun deleteHanja(hanja: Hanja)*/
}