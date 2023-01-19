package com.padcmyanmar.smtz.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CheckoutVO

data class CheckoutResponse (
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("data")
    val data : CheckoutVO?
)