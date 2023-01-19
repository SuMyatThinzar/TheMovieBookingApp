package com.padcmyanmar.smtz.themoviebookingapp

import android.app.Application
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        TheMovieBookingModelImpl.initDatabase(applicationContext)
    }
}