package com.padcmyanmar.smtz.themoviebookingapp.network

import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themoviebookingapp.network.responses.GetCreditsByMoviesResponse
import com.padcmyanmar.smtz.themoviebookingapp.network.responses.GetGenreResponse
import com.padcmyanmar.smtz.themoviebookingapp.network.responses.MovieListResponse
import com.padcmyanmar.smtz.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING_MOVIE)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Call<MovieListResponse>

    @GET(API_GET_COMING_SOON)
    fun getComingSoonMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Call<MovieListResponse>

    @GET("$API_GET_MOVIE_DETAIL{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<MovieVO>

    @GET(API_GET_GENRE)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<GetGenreResponse>

    @GET("$API_GET_CAST/{movie_id}/credits")
    fun getCastByMovies(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<GetCreditsByMoviesResponse>
}