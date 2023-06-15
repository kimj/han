package com.mentalmachines.han.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "grammar",
    indices = [Index("grammar_id")]
)
data class Grammar(
    @PrimaryKey @ColumnInfo(name = "grammar_id") val grammarId: String,
    @ColumnInfo(name = "grammar_description") val grammarDescription: String,
)