package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieDetailDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.MovieViewHolder

class MovieViewPodAdapter(private val mDelegate: MovieDetailDelegate) : RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false)
        return MovieViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        //return mMovieList.count()
        return mMovieList.count()
    }

    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}