package com.mentalmachines.han.data.repository

import androidx.room.Dao
import androidx.room.Query
import com.mentalmachines.han.data.model.Grammar
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [GrammarDao] class.
 */
@Dao
interface GrammarDao {
    @Query("SELECT * FROM grammar")
    fun getGrammar(): Flow<List<Grammar>>

}