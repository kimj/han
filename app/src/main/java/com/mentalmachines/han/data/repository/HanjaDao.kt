package com.mentalmachines.han.data.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [HanjaDao] class.
 */
@Dao
interface HanjaDao {
    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): Flow<List<HanjaDao>>

    @Query("SELECT EXISTS(SELECT 1 FROM garden_plantings WHERE plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): Flow<Boolean>

    /**
     * This query will tell Room to query both the [Plant] and [GardenPlanting] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>>

    @Insert
    suspend fun insertGardenPlanting(hanja: Hanja): Long

    @Delete
    suspend fun deleteGardenPlanting(hanja: Hanja)
}