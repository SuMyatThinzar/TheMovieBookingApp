package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.GenreVO
import kotlinx.android.synthetic.main.view_holder_genre_chips.view.*

class GenreChipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(genre: GenreVO) {
        itemView.tvMovieDetailGenres.text = genre.name ?: ""
    }
}