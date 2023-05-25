package com.mentalmachines.han.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Blob
import java.util.Calendar


data class Hanja {
    val char: String

}

@Entity(
    tableName = "hanjas_content",
    foreignKeys = [
        ForeignKey(
            entity = HanjasContent::class,
            parentColumns = ["docid"],
            childColumns = ["docid"]
        )
    ],
    indices = [Index("docid")]
)
data class HanjasContent(
    @PrimaryKey @ColumnInfo(name = "docid") val docid: Int,
    @ColumnInfo(name = "c0hanja") val c0hanja: Int,
    @ColumnInfo(name = "c1hangul") val c1hangul: Int,
    @ColumnInfo(name = "c2english") val c2english: Int,
    @ColumnInfo(name = "c3hidden_index") val c3hidden_index: Int,
)

@Entity(
    tableName = "hanjas_segments",
    foreignKeys = [
        ForeignKey(
            entity = HanjasSegments::class,
            parentColumns = ["blockid"],
            childColumns = ["blockid"]
        )
    ],
    indices = [Index("blockid")]
)
data class HanjasSegments(
    @ColumnInfo(name = "blockid") val blockid: Int,
    @ColumnInfo(name = "block") val block: Blob,
)

@Entity(
    tableName = "hanjas_segdir",
    foreignKeys = [
        ForeignKey(
            entity = HanjasSegdir::class,
            parentColumns = ["blockid"],
            childColumns = ["blockid"]
        )
    ],
    indices = [Index("blockid")]
)
data class HanjasSegdir(
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "idx") val idx: Int,
    @ColumnInfo(name = "start_block") val start_block: Int,
    @ColumnInfo(name = "leaves_end_block") val leaves_end_block: Int,
    @ColumnInfo(name = "end_block") val end_block: Int,
    @ColumnInfo(name = "root") val root: Blob,
)

@Entity(
    tableName = "hanja_definition_content",
    foreignKeys = [
        ForeignKey(
            entity = HanjaDefinitionContent::class,
            parentColumns = ["id"],
            childColumns = ["plant_id"]
        )
    ],
    indices = [Index("plant_id")]
)
data class HanjaDefinitionContent(
    @PrimaryKey @ColumnInfo(name = "docid") val docid: Int,
    @ColumnInfo(name = "c0hanjas") val c0hanjas: Int,
    @ColumnInfo(name = "c1definition") val c1definition: Int
)

@Entity(
    tableName = "hanja_definition_segments",
    foreignKeys = [
        ForeignKey(
            entity = HanjaDefinitionSegments::class,
            parentColumns = ["blockid"],
            childColumns = ["blockid"]
        )
    ],
    indices = [Index("blockid")]
)
data class HanjaDefinitionSegments(
    @ColumnInfo(name = "blockid") val blockid: Int,
    @ColumnInfo(name = "block") val block: Blob,
)

@Entity(
    tableName = "hanja_definition_segdir",
    foreignKeys = [
        ForeignKey(
            entity = HanjaDefinitionSegdir::class,
            parentColumns = ["blockid"],
            childColumns = ["blockid"]
        )
    ],
    indices = [Index("blockid")]
)
data class HanjaDefinitionSegdir(
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "idx") val idx: Int,
    @ColumnInfo(name = "start_block") val start_block: Int,
    @ColumnInfo(name = "leaves_end_block") val leaves_end_block: Int,
    @ColumnInfo(name = "end_block") val end_block: Int,
    @ColumnInfo(name = "root") val root: Blob,
)

@Entity(
    tableName = "korean_pronounciation_content",
    foreignKeys = [
        ForeignKey(
            entity = Pronounciation::class,
            parentColumns = ["id"],
            childColumns = ["plant_id"]
        )
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
        ForeignKey(entity = Radicals::class, parentColumns = ["id"], childColumns = ["plant_id"])
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
