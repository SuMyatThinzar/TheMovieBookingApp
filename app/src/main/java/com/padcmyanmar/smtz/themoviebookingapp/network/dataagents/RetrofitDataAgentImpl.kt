package com.padcmyanmar.smtz.themoviebookingapp.network.dataagents

import com.padcmyanmar.smtz.themoviebookingapp.data.vos.*
import com.padcmyanmar.smtz.themoviebookingapp.network.TheMovieApi
import com.padcmyanmar.smtz.themoviebookingapp.network.TheMovieBookingApi
import com.padcmyanmar.smtz.themoviebookingapp.network.responses.*
import com.padcmyanmar.smtz.themoviebookingapp.utils.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : TheMovieBookingDataAgent {

    private var mTheMovieBookingApi: TheMovieBookingApi? = null
    private var mTheMovieApi: TheMovieApi? = null

    init {

        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofitTheMovieBooking = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitTheMovieDB = Retrofit.Builder()
            .baseUrl(BASE_URL_THE_MOVIE_DB)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofitTheMovieBooking.create(TheMovieBookingApi::class.java)
        mTheMovieApi = retrofitTheMovieDB.create(TheMovieApi::class.java)
    }

    override fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (UserDataVO,token: String, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.registerUser(name, email, phone, password)
            ?.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(
                                it,
                                response.body()?.token ?: "",
                                response.body()?.message ?: ""
                            )
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFailure(t.message ?: "")

                }
            })
    }

    override fun loginUser(
        email: String,
        password: String,
        onSuccess: (UserDataVO, token: String, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.loginUser(email, password)
            ?.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(
                                it,
                                response.body()?.token ?: "",
                                response.body()?.message ?: ""
                            )
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })
    }

    override fun getProfile(
        token: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getProfile(token)?.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun logoutUser(
        token: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.logoutUser(token)?.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }
        })
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getNowPlayingMovies()?.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: listOf()
                    onSuccess(movieList)
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }
        })
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getComingSoonMovies()?.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: listOf()
                    onSuccess(movieList)
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getMovieDetails(movieId)?.enqueue(object : Callback<MovieVO> {
            override fun onResponse(
                call: Call<MovieVO>,
                response: Response<MovieVO>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                onFailure(t.message ?: "")
            }
        })
    }

    override fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getGenres()?.enqueue(object : Callback<GetGenreResponse> {
            override fun onResponse(
                call: Call<GetGenreResponse>,
                response: Response<GetGenreResponse>
            ) {
                if (response.isSuccessful) {
                    val genreList = response.body()?.genres ?: listOf()
                    onSuccess(genreList)
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<GetGenreResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getCreditsByMovies(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCastByMovies(movieId)
            ?.enqueue(object : Callback<GetCreditsByMoviesResponse> {
                override fun onResponse(
                    call: Call<GetCreditsByMoviesResponse>,
                    response: Response<GetCreditsByMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.cast ?: listOf())
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetCreditsByMoviesResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })
    }

    override fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaDayTimeslot(token, movieId, date)
            ?.enqueue(object : Callback<GetCinemaListResponse> {
                override fun onResponse(
                    call: Call<GetCinemaListResponse>,
                    response: Response<GetCinemaListResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(response.body()?.data ?: listOf())
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetCinemaListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })
    }

    override fun getSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSeatingPlan(token, cinemaDayTimeslotId, bookingDate)
            ?.enqueue(object : Callback<GetSeatingPlanResponse> {
                override fun onResponse(
                    call: Call<GetSeatingPlanResponse>,
                    response: Response<GetSeatingPlanResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            //val seatList = it.flattenedList() ?: listOf()
                            val seatList = it.data.flatten()
                            onSuccess(seatList)
                        }
                    } else {
                        onFailure(response.message())
                        println("$BASE_URL$API_GET_SEATING_PLAN?$PARAM_CINEMA_DAY_TIMESLOT_ID=$cinemaDayTimeslotId&$PARAM_BOOKING_DATE=$bookingDate")
                    }
                }

                override fun onFailure(call: Call<GetSeatingPlanResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getSnack(
        token: String,
        onSuccess: (ArrayList<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSnack(token)?.enqueue(object : Callback<GetSnackResponse> {
            override fun onResponse(
                call: Call<GetSnackResponse>,
                response: Response<GetSnackResponse>
            ) {
                if (response.isSuccessful) {
                    onSuccess(response.body()?.data ?: arrayListOf())
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<GetSnackResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getPaymentMethod(
        token: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getPaymentMethod(token)
            ?.enqueue(object : Callback<GetCreditCardResponse> {
                override fun onResponse(
                    call: Call<GetCreditCardResponse>,
                    response: Response<GetCreditCardResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()?.data ?: listOf())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetCreditCardResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.createCard(token, cardNumber, cardHolder, expirationDate, cvc)
            ?.enqueue(object : Callback<CreateCardResponse> {
                override fun onResponse(
                    call: Call<CreateCardResponse>,
                    response: Response<CreateCardResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let{
                            onSuccess(it.message?:"")
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CreateCardResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun checkout(
        token: String,
        checkoutRequestVO: CheckoutRequestVO,
        onSuccess: (CheckoutVO) -> Unit,
//        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.checkout(token, checkoutRequestVO)
            ?.enqueue(object : Callback<CheckoutResponse> {
                override fun onResponse(
                    call: Call<CheckoutResponse>,
                    response: Response<CheckoutResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let{
                            it.data?.let {data -> onSuccess(data) }
//                            onSuccess(it.message?: "")
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }
}