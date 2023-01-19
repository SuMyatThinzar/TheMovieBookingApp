package com.padcmyanmar.smtz.themoviebookingapp.delegates

interface SignInFragmentToActivityDelegate {
    fun onSendDataSignIn(name:String, email:String, phone:String, password:String)
}