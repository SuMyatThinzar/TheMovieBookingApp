package com.padcmyanmar.smtz.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.CardTouchDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewholders.PaymentMethodViewHolder

class PaymentMethodViewHolderAdapter(private var mDelegate: CardTouchDelegate) : RecyclerView.Adapter<PaymentMethodViewHolder>() {

    private var mCardList : List<CardVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_payment_method, parent, false)
        return PaymentMethodViewHolder(itemView,mDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        if(mCardList.isNotEmpty()){
            holder.bindNewData(mCardList[position])
        }
    }

    override fun getItemCount(): Int {
        return mCardList.size
    }

    fun bindData(cardList: List<CardVO>) {
        mCardList = cardList
        notifyDataSetChanged()
    }
}