package com.padcmyanmar.smtz.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.UserDataVO

data class UserResponse (
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("data")
    val data : UserDataVO?,
    @SerializedName("token")
    val token : String?,
        )