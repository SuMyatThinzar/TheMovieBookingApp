package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieDetailDelegate
import com.padcmyanmar.smtz.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieViewHolder(itemView: View, private val mDelegate: MovieDetailDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                mDelegate.onTapMovie(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {

        mMovie = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMoviePoster)

        itemView.tvMovieName.text = movie.title
    }

}