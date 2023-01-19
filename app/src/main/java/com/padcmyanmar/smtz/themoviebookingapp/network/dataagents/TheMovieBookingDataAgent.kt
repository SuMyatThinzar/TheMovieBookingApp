package com.padcmyanmar.smtz.themoviebookingapp.network.dataagents

import com.padcmyanmar.smtz.themoviebookingapp.data.vos.*

interface TheMovieBookingDataAgent {
    fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (UserDataVO, token: String, message: String) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun loginUser(
        email: String,
        password: String,
        onSuccess: (UserDataVO, token: String, message: String) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getProfile(
        token: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun logoutUser(
        token: String,
        onSuccess: (message: String) -> Unit,
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

    fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovies(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnack(
        token: String,
        onSuccess: (ArrayList<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        token: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkout(
        token: String,
        checkoutRequestVO: CheckoutRequestVO,
        onSuccess: (CheckoutVO) -> Unit,
//        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )
}