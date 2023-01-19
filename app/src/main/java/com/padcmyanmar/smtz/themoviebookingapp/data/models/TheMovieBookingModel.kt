package com.padcmyanmar.smtz.themoviebookingapp.data.models

import com.padcmyanmar.smtz.themoviebookingapp.data.vos.*

interface TheMovieBookingModel {
    fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess : (message: String) -> Unit,
        onFailure : (String) -> Unit,
    )

    fun loginUser(
        email: String,
        password: String,
        onSuccess : (message: String) -> Unit,
        onFailure : (String) -> Unit,
    )

    fun getProfile(
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logoutUser(
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditByMovies(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeSlot(
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnack(
        onSuccess: (ArrayList<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkout(
        checkoutRequestVO: CheckoutRequestVO,
        onSuccess: (CheckoutVO) -> Unit,
//        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )
}