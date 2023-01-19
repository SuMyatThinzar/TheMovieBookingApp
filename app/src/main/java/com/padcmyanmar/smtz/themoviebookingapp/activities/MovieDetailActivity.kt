package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.adapters.CastViewHolderAdapter
import com.padcmyanmar.smtz.themoviebookingapp.adapters.GenreChipsAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themoviebookingapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.smtz.themoviebookingapp.utils.IMAGE_BASE_URL_2
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mCastAdapter: CastViewHolderAdapter
    lateinit var mGenreChipsAdapter: GenreChipsAdapter

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl

    private var mGenres: List<GenreVO>? = null

    private var mMovieId: Int = 0
    private var mMovieName: String? = null

    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA MOVIE ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)

        setUpAdapters()
        setUpListeners()

        requestData(mMovieId)

    }

    private fun requestData(movieId: Int) {
        mTheMovieBookingModel.getMovieDetails(
            movieId = movieId.toString(),
            onSuccess = {
                mMovieName = it.title
                bindData(it, movieId)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun bindData(movie: MovieVO, movieId: Int) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL_2${movie.posterPath}")
            .into(ivMovieDetailPoster)

        tvMovieDetailName.text = movie.title ?: ""
        tvMovieDetailLength.text = movie.getMovieRunTime() ?: ""
        rbMovieDetailRating.rating = movie.getRatingBasedOnFiveStar()
        tvMovieDetailImdb.text = "IMDb ${movie.voteAverage}"
        tvPlotSummary.text = movie.overview ?: ""

        bindGenres()
        bindCasts(movieId)

    }

    private fun bindGenres() {
        mTheMovieBookingModel.getGenres(
            onSuccess = {
                mGenres = it
                mGenreChipsAdapter.bindGenreData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun bindCasts(movieId: Int) {
        mTheMovieBookingModel.getCreditByMovies(
            movieId = movieId.toString(),
            onSuccess = {
                mCastAdapter.bindNewData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListeners() {

        btnDetailScreenBack.setOnClickListener {
            super.onBackPressed()
        }

        btnDetailGetYourTicket.setOnClickListener {
            startActivity(TimeScreenActivity.newIntent(this, mMovieId, mMovieName))
        }
    }

    private fun setUpAdapters() {

        mCastAdapter = CastViewHolderAdapter()
        rvCast.adapter = mCastAdapter
        rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mGenreChipsAdapter = GenreChipsAdapter()
        rvGenreChips.adapter = mGenreChipsAdapter
        rvGenreChips.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

}