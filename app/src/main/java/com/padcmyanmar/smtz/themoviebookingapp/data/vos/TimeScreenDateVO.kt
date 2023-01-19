package com.padcmyanmar.smtz.themoviebookingapp.data.vos

import androidx.room.*
import com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters.CinemaListTypeConverter

@Entity(tableName = "dates")
@TypeConverters(
    CinemaListTypeConverter::class,
)

data class TimeScreenDateVO (
    var isSelected: Boolean = false,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "day")
    var day: String?,

    @PrimaryKey
    var dateFull : String,

    @ColumnInfo(name = "dateInstant")
    var dateInstant: String?,

    @ColumnInfo(name = "cinemaList")
    var cinemaList: List<CinemaVO>? = listOf(),
        )