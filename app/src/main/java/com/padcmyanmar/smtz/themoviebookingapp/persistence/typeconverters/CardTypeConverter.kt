package com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO

class CardTypeConverter {

    @TypeConverter
    fun toString(cards : List<CardVO>) : String {
        return Gson().toJson(cards)
    }

    @TypeConverter
    fun toCards(cardsJsonString: String) : List<CardVO>?{
        val cardListType = object : TypeToken<List<CardVO>?>() {}.type
        return Gson().fromJson(cardsJsonString,cardListType)
    }

}