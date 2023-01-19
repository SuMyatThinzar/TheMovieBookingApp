package com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CinemaVO

class CinemaListTypeConverter {

    @TypeConverter
    fun toString(cinemaList : List<CinemaVO>?) : String{
        return Gson().toJson(cinemaList)
    }
    @TypeConverter
    fun toCinemaList(cinemaListJsonString: String) : List<CinemaVO>? {
        val cinemaListType = object : TypeToken<List<CinemaVO>?>() {}.type
        return Gson().fromJson(cinemaListJsonString,cinemaListType)
    }
}