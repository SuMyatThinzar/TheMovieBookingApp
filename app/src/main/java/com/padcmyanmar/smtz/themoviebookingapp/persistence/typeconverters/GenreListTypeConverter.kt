package com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.GenreVO

class GenreListTypeConverter {

    @TypeConverter
    fun toString(genresList : List<GenreVO>?) : String{
        return Gson().toJson(genresList)
    }
    @TypeConverter
    fun toGenresList(genresListJsonString: String) : List<GenreVO>? {
        val genresListType = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genresListJsonString,genresListType)
    }
}