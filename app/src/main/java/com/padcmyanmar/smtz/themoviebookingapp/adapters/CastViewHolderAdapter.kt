package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.CastViewHolder

class CastViewHolderAdapter : RecyclerView.Adapter<CastViewHolder>() {

    private var actorList : List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast,parent,false)
        return CastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if(actorList.isNotEmpty()){
            holder.bindData(actorList[position])
        }
    }

    override fun getItemCount(): Int {
        return actorList.count()
    }

    fun bindNewData(actorList: List<ActorVO>) {
        this.actorList = actorList
        notifyDataSetChanged()
    }
}