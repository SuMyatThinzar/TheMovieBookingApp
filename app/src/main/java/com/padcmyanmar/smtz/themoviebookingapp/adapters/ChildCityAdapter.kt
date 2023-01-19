package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeSlotVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeslotDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.ChildCityViewHolder
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.HostCityViewHolder

class ChildCityAdapter(private val mDelegate: TimeslotDelegate) : RecyclerView.Adapter<ChildCityViewHolder>() {

    private var mChildList : List<TimeSlotVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildCityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_child_city,parent, false)
        return ChildCityViewHolder(itemView,mDelegate)
    }

    override fun onBindViewHolder(holder: ChildCityViewHolder, position: Int) {
        if (mChildList.isNotEmpty()){
            holder.bindNewData(mChildList[position])
        }
    }

    override fun getItemCount(): Int {
        return mChildList.size
    }

    fun bindData(childList: List<TimeSlotVO>) {
        mChildList = childList
        notifyDataSetChanged()
    }
}