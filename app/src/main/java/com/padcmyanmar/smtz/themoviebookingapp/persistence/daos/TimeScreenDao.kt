package com.padcmyanmar.smtz.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CinemaVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.DateCinemaAndTimeslotVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeScreenDateVO

@Dao
interface TimeScreenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDateCinemaAndTimeslots(dateCinemaAndTimeslot: DateCinemaAndTimeslotVO)

    @Query("SELECT * FROM dateCinemaAndTimeslots WHERE date= :date")
    fun getCinemasByDate(date: String) : DateCinemaAndTimeslotVO

    @Query("DELETE FROM dateCinemaAndTimeslots")
    fun deleteAllCinemas()
}