package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeScreenDateVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeSlotVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeScreenDateButtonDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeslotDelegate
import kotlinx.android.synthetic.main.view_holder_child_city.view.*
import kotlinx.android.synthetic.main.view_holder_date.view.*

class ChildCityViewHolder(itemView: View, private val mDelegate: TimeslotDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mTimeslot : TimeSlotVO? = null

    init {
        itemView.setOnClickListener{
            mTimeslot?.let{
                mDelegate.onTapTimeslot(it.cinemaDayTimeslotId ?: 0)
            }
        }
    }

    fun bindNewData(timeslot: TimeSlotVO) {
        mTimeslot = timeslot

        itemView.tvTime.text = timeslot.startTime

        setColor(timeslot)
    }

    private fun setColor(timeslot: TimeSlotVO){

        if(timeslot.isSelected == true){
            itemView.tvTime.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            itemView.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_time_button_selected
            )
        } else {
            itemView.tvTime.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            itemView.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_time_button
            )
        }
    }
}