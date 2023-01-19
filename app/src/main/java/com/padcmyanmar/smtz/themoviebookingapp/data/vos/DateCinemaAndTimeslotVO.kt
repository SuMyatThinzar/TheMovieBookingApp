package com.padcmyanmar.smtz.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters.CinemaListTypeConverter

@Entity(tableName = "dateCinemaAndTimeslots")
@TypeConverters(
    CinemaListTypeConverter::class,
)

class DateCinemaAndTimeslotVO(

    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "cinemaList")
    var cinemas: List<CinemaVO>? = null
)