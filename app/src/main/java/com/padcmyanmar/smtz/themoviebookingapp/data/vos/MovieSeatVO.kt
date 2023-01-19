package com.padcmyanmar.smtz.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

class MovieSeatVO(
    var isSelected: Boolean? = false,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("seat_name")
    val seatName: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("price")
    val price: String = ""
    ) {

    fun isMovieSeatAvailable(): Boolean {
        return type == SEAT_TYPE_AVAILABLE
    }
    fun isMovieSeatTaken(): Boolean {
        return type == SEAT_TYPE_TAKEN
    }
    fun isMovieSeatRowText(): Boolean{
        return type == SEAT_TYPE_TEXT
    }
}

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TAKEN = "taken"
const val SEAT_TYPE_EMPTY = "space"
const val SEAT_TYPE_TEXT = "text"