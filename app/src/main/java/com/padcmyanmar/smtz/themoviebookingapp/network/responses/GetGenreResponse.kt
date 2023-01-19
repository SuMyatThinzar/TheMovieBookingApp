package com.padcmyanmar.smtz.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.GenreVO

data class GetGenreResponse(
    @SerializedName("genres")
    val genres : List<GenreVO>?
) {
}