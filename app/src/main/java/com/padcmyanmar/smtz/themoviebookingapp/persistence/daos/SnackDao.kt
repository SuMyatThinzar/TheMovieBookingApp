package com.padcmyanmar.smtz.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.SnackVO

@Dao
interface SnackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnackList(snackList: List<SnackVO>)

    @Query("SELECT * FROM snacks")
    fun getAllSnacks() : List<SnackVO>

    @Query("DELETE FROM snacks")
    fun deleteAllSnacks()
}