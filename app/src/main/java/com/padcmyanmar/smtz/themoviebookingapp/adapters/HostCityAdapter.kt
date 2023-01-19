package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CinemaVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeslotDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.HostCityViewHolder

class HostCityAdapter(private val mDelegate: TimeslotDelegate) : RecyclerView.Adapter<HostCityViewHolder>() {

    private var mCinemaList : List<CinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostCityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_host_city,parent, false)
        return HostCityViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: HostCityViewHolder, position: Int) {
        if (mCinemaList.isNotEmpty()){
            holder.bindNewData(mCinemaList[position], mCinemaList[position].timeslots)
        }
    }

    override fun getItemCount(): Int {
        return mCinemaList.count()
    }

    fun bindData(cinemaList: List<CinemaVO>) {
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }

}