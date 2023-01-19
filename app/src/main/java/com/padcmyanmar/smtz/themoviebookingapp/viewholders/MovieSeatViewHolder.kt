package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieSeatDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatViewHolder(itemView: View, private var mDelegate: MovieSeatDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieSeatVO : MovieSeatVO? = null

    //Adapter ka nay call mhr
    fun bindData(data: MovieSeatVO) {

        mMovieSeatVO = data

        when {
            data.isMovieSeatAvailable() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_availible
                )
            }
            data.isMovieSeatTaken() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
            }
            data.isMovieSeatRowText() -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.tvMovieSeatTitle.textSize = 10F
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
            else -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
        }

        itemView.setOnClickListener {
            mMovieSeatVO?.let {
                mDelegate.onTapMovieSeat(
                    seatName = it.seatName ?: "",
                )
            }
        }

        if( data.isSelected == true) {
            itemView.tvMovieSeatTitle.visibility = View.VISIBLE
            itemView.tvMovieSeatTitle.text = data.seatName
            itemView.tvMovieSeatTitle.setTextColor(Color.WHITE)
            itemView.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_movie_seat_selected
            )
            itemView.tvMovieSeatTitle.textSize = 6F
        }
    }
}