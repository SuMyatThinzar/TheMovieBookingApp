package com.padcmyanmar.smtz.themoviebookingapp.data.models

import android.content.Context
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.*
import com.padcmyanmar.smtz.themoviebookingapp.network.dataagents.RetrofitDataAgentImpl
import com.padcmyanmar.smtz.themoviebookingapp.network.dataagents.TheMovieBookingDataAgent
import com.padcmyanmar.smtz.themoviebookingapp.persistence.MovieDatabase

object TheMovieBookingModelImpl : TheMovieBookingModel {

    private var token: String? = null

    private val mTheMovieBookingDataAgent: TheMovieBookingDataAgent = RetrofitDataAgentImpl

    private var mMovieDatabase: MovieDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

    override fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.registerUser(
            name, email, phone, password,
            onSuccess = { user, token, message ->
                this.token = "Bearer $token"

                val userFromDatabase = mMovieDatabase?.userDao()?.getUser(token)
                this.token.let {
                    userFromDatabase?.token = this.token ?: ""
                }

                user.token = "Bearer $token"
                mMovieDatabase?.userDao()?.insertUser(user)

                onSuccess(message)
            }, onFailure = onFailure
        )
    }

    override fun loginUser(
        email: String,
        password: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.loginUser(
            email, password,
            onSuccess = { user, token, message ->
                this.token = "Bearer $token"

                val userFromDatabase = mMovieDatabase?.userDao()?.getUser(token)
                this.token.let {
                    userFromDatabase?.token = this.token ?: ""
                }

                user.token = "Bearer $token"
                mMovieDatabase?.userDao()?.insertUser(user)
                onSuccess(message)

            },
            onFailure = onFailure
        )
    }

    override fun getProfile(
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDatabase?.userDao()?.getToken()?.token?.let {
        this.token?.let{
            val userFromDatabase = mMovieDatabase?.userDao()?.getUser(token = it)
            userFromDatabase?.let { userFromDb ->
                onSuccess(userFromDb)
            }

            mTheMovieBookingDataAgent.getProfile(
                it,
                onSuccess = { user ->
                    user.token = it
                    mMovieDatabase?.userDao()?.insertUser(user)
                    onSuccess(user)
                }, onFailure = onFailure
            )
        }
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        mTheMovieBookingDataAgent.getNowPlayingMovies(
            onSuccess = {
                it.forEach { movie ->
                    movie.type = NOW_PLAYING
                }
                mMovieDatabase?.movieDao()?.insertMovies(it)
                onSuccess(it)
            }, onFailure
        )
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = COMING_SOON) ?: listOf())

        mTheMovieBookingDataAgent.getComingSoonMovies(
            onSuccess = {
                it.forEach { movie ->
                    movie.type = COMING_SOON
                }
                mMovieDatabase?.movieDao()?.insertMovies(it)
                onSuccess(it)
            }, onFailure
        )
    }

    override fun logoutUser(
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.token?.let {
            mTheMovieBookingDataAgent.logoutUser(
                token = it,
                onSuccess = { message ->
                    this.token = null
                    onSuccess(message)
                },
                onFailure = onFailure
            )
        }
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
        movieFromDatabase?.let {
            onSuccess(it)
        }

        mTheMovieBookingDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = {
                val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                it.type = movieFromDatabase?.type

                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieBookingDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure) }

    override fun getCreditByMovies(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getCreditsByMovies(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCinemaDayTimeSlot(
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieDatabase?.TimeScreenDao()?.getCinemasByDate(date)?.cinemas ?: listOf())

        this.token?.let {
            mTheMovieBookingDataAgent.getCinemaDayTimeslot(
                token = it,
                movieId = movieId,
                date = date,
                onSuccess = { cinemaList ->
                    val dateCinemaAndTimeslot = DateCinemaAndTimeslotVO(
                        date = date,
                        cinemas = cinemaList
                    )
                    //database
                    mMovieDatabase?.TimeScreenDao()?.insertDateCinemaAndTimeslots(dateCinemaAndTimeslot)
                    onSuccess(cinemaList)
                }, onFailure = onFailure,
            )
        }
    }

    override fun getSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.token?.let {
            mTheMovieBookingDataAgent.getSeatingPlan(
                token = it,
                cinemaDayTimeslotId = cinemaDayTimeslotId,
                bookingDate = bookingDate,
                onSuccess = onSuccess,
                onFailure = onFailure,
            )
        }
    }

    override fun getSnack(
        onSuccess: (ArrayList<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess((mMovieDatabase?.snackDao()?.getAllSnacks() ?: listOf()) as ArrayList<SnackVO>)    //List -> ArrayList
//        onSuccess(ArrayList(mMovieDatabase?.movieBookingDao()?.getAllSnacks() ?: listOf()))

        this.token?.let { it ->
            mTheMovieBookingDataAgent.getSnack(
                token = it,
                onSuccess = { snackArrayList ->
                    val snack = snackArrayList.toList()                             //ArrayList -> List
                    mMovieDatabase?.snackDao()?.insertSnackList(snack)
                    onSuccess(snackArrayList)
                },
                onFailure = onFailure
            )
        }
    }

    //Snack Screen Card List
    override fun getPaymentMethod(onSuccess: (List<CardVO>) -> Unit, onFailure: (String) -> Unit) {
        onSuccess(mMovieDatabase?.cardDao()?.getAllCards() ?: listOf())

        this.token?.let {
            mTheMovieBookingDataAgent.getPaymentMethod(
                token = it,
                onSuccess = { cardList ->
                    mMovieDatabase?.cardDao()?.insertCardList(cardList)
                    onSuccess(cardList)
                },
                onFailure = onFailure
            )
        }
    }

    override fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {

        this.token?.let {
            mTheMovieBookingDataAgent.createCard(
                token = it,
                cardNumber = cardNumber,
                cardHolder = cardHolder,
                expirationDate = expirationDate,
                cvc = cvc,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun checkout(
        checkoutRequestVO: CheckoutRequestVO,
        onSuccess: (CheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.token?.let {
            mTheMovieBookingDataAgent.checkout(
                token = it,
                checkoutRequestVO = checkoutRequestVO,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }
}
