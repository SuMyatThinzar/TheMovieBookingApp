package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.GenreChipsViewHolder

class GenreChipsAdapter : RecyclerView.Adapter<GenreChipsViewHolder>() {

    private var mGenreList : List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreChipsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_genre_chips,parent,false)
        return GenreChipsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenreChipsViewHolder, position: Int) {
        if(mGenreList.isNotEmpty()){
            holder.bindData(mGenreList[position])
        }
    }

    override fun getItemCount(): Int {
        return mGenreList.count()
    }

    fun bindGenreData(genreList: List<GenreVO>) {
        mGenreList = genreList
        notifyDataSetChanged()
    }
}