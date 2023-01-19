package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.CardTouchDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SnackQuantityButtonDelegate
import kotlinx.android.synthetic.main.activity_snack_screen.view.*
import kotlinx.android.synthetic.main.view_holder_combo_set.view.*
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class PaymentMethodViewHolder(itemView: View, private var mDelegate: CardTouchDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mCard : CardVO? = null

    init {
        itemView.setOnClickListener {
            mCard?.let {
                mDelegate.onTapCard(mCard!!.id)
            }
        }
    }


    fun bindNewData(card: CardVO) {
        mCard = card

        itemView.tvCreditCardTitle.text = card.name
        itemView.tvCreditCardComponents.text = card.description

        if(card.isSelected == true){
            itemView.tvCreditCardTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            itemView.tvCreditCardComponents.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
        } else {
            itemView.tvCreditCardTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            itemView.tvCreditCardComponents.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
        }
    }
}