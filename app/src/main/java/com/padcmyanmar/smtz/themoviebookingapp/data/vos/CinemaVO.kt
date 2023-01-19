package com.padcmyanmar.smtz.themoviebookingapp.data.vos

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters.TimeSlotTypeConverter

//@Entity(tableName = "cinemas")
@TypeConverters(
    TimeSlotTypeConverter::class,
)

data class CinemaVO(

    @SerializedName("cinema_id")
//    @PrimaryKey
    val cinemaId: Int?,

    @SerializedName("cinema")
//    @ColumnInfo(name = "cinema")
    val cinema: String?,

    @SerializedName("timeslots")
//    @ColumnInfo(name = "timeslots")
    val timeslots: List<TimeSlotVO>?,
)