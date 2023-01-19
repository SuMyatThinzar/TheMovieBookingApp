package com.padcmyanmar.smtz.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.ActorVO

data class GetCreditsByMoviesResponse (
    @SerializedName("cast")
    val cast : List<ActorVO>?,
    @SerializedName("crew")
    val crew : List<ActorVO>?,
    )