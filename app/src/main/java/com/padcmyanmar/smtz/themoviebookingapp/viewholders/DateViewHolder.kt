package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeScreenDateVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeScreenDateButtonDelegate
import kotlinx.android.synthetic.main.view_holder_date.view.*

class DateViewHolder(itemView: View, private val mDelegate: TimeScreenDateButtonDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mDate : TimeScreenDateVO? = null

    init {
        itemView.setOnClickListener{
            mDate?.let{
                mDelegate.onTapDatePicker(it.dateFull ?: "")
            }
        }
    }

    fun bindNewData(date: TimeScreenDateVO) {
        mDate = date

        itemView.tvDayLabel.text = date.date ?: ""
        itemView.tvDay.text = date.day ?: ""

        if(date.isSelected){
            itemView.tvDayLabel.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            itemView.tvDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
        } else {
            itemView.tvDayLabel.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryText))
            itemView.tvDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryText))
        }
    }
}