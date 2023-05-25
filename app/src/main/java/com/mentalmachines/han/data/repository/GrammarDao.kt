package com.mentalmachines.han.data.repository

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [GrammarDao] class.
 */
@Dao
interface GrammarDao {
    @Query("SELECT * FROM grammar")
    fun getHanja(): Flow<List<GrammarDao>>

}