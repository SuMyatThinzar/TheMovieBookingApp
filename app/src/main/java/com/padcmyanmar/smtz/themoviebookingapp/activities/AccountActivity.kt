package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.adapters.AccountViewPagerAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.delegates.AccountButtonActionDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.LogInFragmentToActivityDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SignInFragmentToActivityDelegate
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity(), AccountButtonActionDelegate,
    SignInFragmentToActivityDelegate, LogInFragmentToActivityDelegate {

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl

    private var name: String = ""
    private var email: String = ""
    private var phone: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        setUpLoginViewPagerAdapter()
        setUpLoginTab()

    }

//    private fun requestDataForHomeScreen(){
//        mTheMovieBookingModel.getProfile(
//            onSuccess = {
//                HomeScreenActivity().setNewData(it)
//            },
//            onFailure = {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
//        )
//
//    }

    private fun setUpLoginTab() {
        TabLayoutMediator(tabLayoutLogin, viewPagerLogin) { tab, position ->
            when (position) {
                0 -> tab.text = "Login"
                else -> tab.text = "Sign in"
            }
        }.attach()
    }

    private fun setUpLoginViewPagerAdapter() {

        val viewPagerAdapter = AccountViewPagerAdapter(this)
        viewPagerLogin.adapter = viewPagerAdapter
    }

    //Button Delegate
    override fun onTapConfirm() {
        startActivity(HomeScreenActivity.newIntent(this))
    }

    //Delegate data from Fragment
    override fun onSendDataSignIn(name: String, email: String, phone: String, password: String) {
        this.name = name
        this.email = email
        this.phone = phone
        this.password = password
        requestDataSignIn(name, email, phone, password)
    }

    private fun requestDataSignIn(name: String, email: String, phone: String, password: String) {
        mTheMovieBookingModel.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeScreenActivity::class.java))
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }

    //delegate data from Fragment
    override fun onSendDataLogIn(email: String, password: String) {
        this.email = email
        this.password = password
        requestDataLogIn(email, password)
    }

    private fun requestDataLogIn(email: String, password: String) {
        mTheMovieBookingModel.loginUser(
            email = email,
            password = password,
            onSuccess = {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeScreenActivity::class.java))
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }

}