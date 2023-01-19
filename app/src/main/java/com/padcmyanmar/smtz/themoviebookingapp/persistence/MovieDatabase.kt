package com.padcmyanmar.smtz.themoviebookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.*
import com.padcmyanmar.smtz.themoviebookingapp.persistence.daos.*

@Database(entities = [UserDataVO::class, MovieVO::class, SnackVO::class, CardVO::class, TimeScreenDateVO::class, DateCinemaAndTimeslotVO::class], version = 10, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    companion object{
        const val DB_NAME = "THE MOVIE DB"

        var dbInstance : MovieDatabase? = null

        fun getDBInstance(context: Context): MovieDatabase? {

            when(dbInstance){
                null -> dbInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance
        }
    }

    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun cardDao(): CardDao
    abstract fun snackDao(): SnackDao
    abstract fun TimeScreenDao(): TimeScreenDao
}