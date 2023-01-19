package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeScreenDateVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeScreenDateButtonDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.DateViewHolder

class DateViewHolderAdapter(private val mDelegate: TimeScreenDateButtonDelegate) : RecyclerView.Adapter<DateViewHolder>() {

    private var mDateList : List<TimeScreenDateVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date,parent,false)
        return DateViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        if(mDateList.isNotEmpty()){
            holder.bindNewData(mDateList[position])
        }
    }

    override fun getItemCount(): Int {
        return mDateList.count()
    }

    fun bindData(dateList: MutableList<TimeScreenDateVO>) {
        mDateList = dateList
        notifyDataSetChanged()
    }



}