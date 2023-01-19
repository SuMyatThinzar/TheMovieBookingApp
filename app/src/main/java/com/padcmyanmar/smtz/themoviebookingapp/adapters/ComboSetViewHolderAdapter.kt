package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SnackQuantityButtonDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.ComboSetViewHolder

class ComboSetViewHolderAdapter(private var mDelegate: SnackQuantityButtonDelegate) : RecyclerView.Adapter<ComboSetViewHolder>() {

    private var mSnackList : List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboSetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_combo_set,parent, false)
        return ComboSetViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: ComboSetViewHolder, position: Int) {
        if(mSnackList.isNotEmpty()){
            holder.bindNewData(mSnackList[position])
        }
    }

    override fun getItemCount(): Int {
        return mSnackList.count()
    }

    fun bindData(snackList: List<SnackVO>,) {
        mSnackList = snackList
        notifyDataSetChanged()
    }
}