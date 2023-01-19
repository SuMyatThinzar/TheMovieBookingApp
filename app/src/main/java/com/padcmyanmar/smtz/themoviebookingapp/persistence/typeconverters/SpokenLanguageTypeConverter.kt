package com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themovieapp.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {

    @TypeConverter
    fun toString(spokenLanguages : List<SpokenLanguageVO>?) : String{
        return Gson().toJson(spokenLanguages)
    }
    @TypeConverter
    fun toSpokenLanguages(spokenLanguageListJsonString: String) : List<SpokenLanguageVO>? {
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguageVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListJsonString,spokenLanguageListType)
    }
}