package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_cast.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(actor: ActorVO) {
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profilePath}")
            .into(itemView.ivMovieDetailCast)
    }
}