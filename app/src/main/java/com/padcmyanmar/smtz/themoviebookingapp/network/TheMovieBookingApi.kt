package com.padcmyanmar.smtz.themoviebookingapp.network

import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CheckoutRequestVO
import com.padcmyanmar.smtz.themoviebookingapp.network.responses.*
import com.padcmyanmar.smtz.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {
    @POST(API_REGISTER_USER)
    @FormUrlEncoded
    fun registerUser(
        @Field(PARAM_NAME) name: String,
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PHONE) phone: String,
        @Field(PARAM_PASSWORD) password: String,
    ): Call<UserResponse>

    @POST(API_LOGIN_USER)
    @FormUrlEncoded
    fun loginUser(
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PASSWORD) password: String,
    ): Call<UserResponse>

    @GET(API_PROFILE)
    fun getProfile(
        @Header(PARAM_AUTH) token: String,
    ): Call<UserResponse>

    @POST(API_LOGOUT)
    fun logoutUser(
        @Header(PARAM_AUTH) token: String,
    ) : Call<UserResponse>

    @GET(API_GET_CINEMA_DAY_TIMESLOT)
    fun getCinemaDayTimeslot(
        @Header(PARAM_AUTH) token: String,
        @Query("movie_id") movieId: String,
        @Query("date") date: String,
    ) : Call<GetCinemaListResponse>

    @GET(API_GET_SEATING_PLAN)
    fun getSeatingPlan(
        @Header(PARAM_AUTH) token: String,
        @Query(PARAM_CINEMA_DAY_TIMESLOT_ID) cinemaDayTimeslotId: String,
        @Query(PARAM_BOOKING_DATE) bookingDate: String,
    ) : Call<GetSeatingPlanResponse>

    @GET(API_GET_SNACK)
    fun getSnack(
        @Header(PARAM_AUTH) token: String,
    ) : Call<GetSnackResponse>

    @GET(API_GET_PAYMENT_METHOD)
    fun getPaymentMethod(
        @Header(PARAM_AUTH) token: String,
    ) : Call<GetCreditCardResponse>

    @POST(API_CREATE_CARD)
    @FormUrlEncoded
    fun createCard(
        @Header(PARAM_AUTH) token: String,
        @Field("card_number") cardNumber: String,
        @Field("card_holder") cardHolder: String,
        @Field("expiration_date") expirationDate: String,
        @Field("cvc") cvc: String,
    ) : Call<CreateCardResponse>

    @POST(API_CHECKOUT)
    fun checkout(
        @Header(PARAM_AUTH) token: String,
        @Body checkoutRequestVO : CheckoutRequestVO,
    ) : Call<CheckoutResponse>
}