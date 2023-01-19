package com.padcmyanmar.smtz.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CinemaVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeSlotVO

class TimeSlotTypeConverter {

    @TypeConverter
    fun toString(timeslotList : List<TimeSlotVO>) : String {
        return Gson().toJson(timeslotList)
    }

    @TypeConverter
    fun toTimeslots(timeslotsJsonString: String) : List<TimeSlotVO>?{
        val timeslotListType = object : TypeToken<List<TimeSlotVO>?>() {}.type
        return Gson().fromJson(timeslotsJsonString,timeslotListType)
    }
}