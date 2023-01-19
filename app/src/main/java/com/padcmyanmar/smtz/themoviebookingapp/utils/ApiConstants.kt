package com.padcmyanmar.smtz.themoviebookingapp.utils

const val BASE_URL = "https://tmba.padc.com.mm"
const val BASE_URL_THE_MOVIE_DB = "https://api.themoviedb.org"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w400/";
const val IMAGE_BASE_URL_2 = "https://image.tmdb.org/t/p/w500/";

const val API_REGISTER_USER = "/api/v1/register";
const val API_LOGIN_USER = "/api/v1/email-login";
const val API_PROFILE = "/api/v1/profile";
const val API_LOGOUT = "/api/v1/logout"
const val API_GET_NOW_PLAYING_MOVIE = "/3/movie/now_playing"
const val API_GET_COMING_SOON = "/3/movie/upcoming"
const val API_GET_MOVIE_DETAIL = "/3/movie/"
const val API_GET_GENRE = "/3/genre/movie/list"
const val API_GET_CAST = "/3/movie"                                             //get_credit_by_movies
const val API_GET_CINEMA_DAY_TIMESLOT = "/api/v1/cinema-day-timeslots"
const val API_GET_SEATING_PLAN = "/api/v1/seat-plan"
const val API_GET_SNACK = "/api/v1/snacks"
const val API_GET_PAYMENT_METHOD = "/api/v1/payment-methods"
const val API_CREATE_CARD = "/api/v1/card"
const val API_CHECKOUT = "/api/v1/checkout"

//params
const val PARAM_API_KEY = "api_key"
const val PARAM_PAGE = "page"
const val PARAM_NAME = "name"
const val PARAM_EMAIL = "email"
const val PARAM_PHONE = "phone"
const val PARAM_PASSWORD = "password"
const val PARAM_AUTH = "Authorization"
const val PARAM_GENRE_ID = "with_genres"
const val PARAM_CINEMA_DAY_TIMESLOT_ID = "cinema_day_timeslot_id"
const val PARAM_BOOKING_DATE = "booking_date"

const val MOVIE_API_KEY = "6674ccf1b2849319f94cff83acc5b54d"