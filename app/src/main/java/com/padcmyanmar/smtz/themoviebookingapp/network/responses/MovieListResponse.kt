package com.padcmyanmar.smtz.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.DateVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO

data class MovieListResponse (
    @SerializedName("page")
    val page : Int?,

    @SerializedName("dates")
    val dates : DateVO?,

    @SerializedName("results")
    val results : List<MovieVO>?
)