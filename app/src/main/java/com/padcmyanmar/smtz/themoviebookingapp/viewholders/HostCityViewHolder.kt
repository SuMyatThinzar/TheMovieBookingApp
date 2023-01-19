package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.adapters.ChildCityAdapter
import com.padcmyanmar.smtz.themoviebookingapp.adapters.HostCityAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CinemaVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeSlotVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeslotDelegate
import kotlinx.android.synthetic.main.view_holder_host_city.view.*

class HostCityViewHolder(itemView: View, mDelegate: TimeslotDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mChildList : List<TimeSlotVO> = listOf()

    private var childRecyclerView  = itemView.rvChildCity
    private var mTimeScreenChildAdapter : ChildCityAdapter = ChildCityAdapter(mDelegate)

    init {
        childRecyclerView.adapter = mTimeScreenChildAdapter
        childRecyclerView.layoutManager = GridLayoutManager(itemView.context, 3)
    }

    //Child timeslot
    fun bindNewData(cinema: CinemaVO, timeslotsList: List<TimeSlotVO>?) {
        itemView.tvCity.text = cinema.cinema

        if (timeslotsList != null) {
            mChildList = timeslotsList
        }
        mTimeScreenChildAdapter.bindData(mChildList)
    }
}