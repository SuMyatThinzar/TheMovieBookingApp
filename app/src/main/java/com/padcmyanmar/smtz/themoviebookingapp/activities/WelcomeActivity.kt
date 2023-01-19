package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.padcmyanmar.smtz.themoviebookingapp.R
import kotlinx.android.synthetic.main.activity_welcome.*
import java.text.DateFormat
import java.util.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setUpOnClickListener()
    }

    private fun setUpOnClickListener() {
        btnGetStarted.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}