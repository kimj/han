package com.mentalmachines.han.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mentalmachines.han.data.repository.Hanja
import java.util.Calendar

class Grammar

data class Hanja(
    @ColumnInfo(name = "plant_id") val plantId: String,
    @ColumnInfo(name = "plant_date") val plantDate: String,
    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}

@Entity(
    tableName = "korean_pronounciation_content",
    foreignKeys = [
        ForeignKey(entity = Hanja::class, parentColumns = ["id"], childColumns = ["plant_id"])
    ],
    indices = [Index("plant_id")]
)
data class Pronounciation(
    @ColumnInfo(name = "plant_id") val plantId: String,
    @ColumnInfo(name = "plant_date") val plantDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}

@Entity(
    tableName = "radicals_content",
    foreignKeys = [
        ForeignKey(entity = Hanja::class, parentColumns = ["id"], childColumns = ["plant_id"])
    ],
    indices = [Index("plant_id")]
)
data class Radicals(
    @ColumnInfo(name = "plant_id") val plantId: String,
    @ColumnInfo(name = "plant_date") val plantDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}