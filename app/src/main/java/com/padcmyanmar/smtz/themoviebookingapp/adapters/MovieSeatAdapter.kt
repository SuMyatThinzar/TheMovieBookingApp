package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieSeatDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.DateViewHolder
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.MovieSeatViewHolder

class MovieSeatAdapter(private var mMovieSeats: List<MovieSeatVO> = listOf(), private var mDelegate: MovieSeatDelegate) : RecyclerView.Adapter<MovieSeatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_seat,parent,false)
        return MovieSeatViewHolder(itemView,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
        if(mMovieSeats.isNotEmpty()){                                                 //App sa sa phwint chin mhr empty list phyit ny loh error tat
            holder.bindData(mMovieSeats[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieSeats.count()
    }

    fun setNewData(movieSeats: List<MovieSeatVO>){
        this.mMovieSeats = movieSeats
        notifyDataSetChanged()                                                       //onBindViewHolder ko invoke pyan lok tr , new data nae pyan bind tr
    }
}