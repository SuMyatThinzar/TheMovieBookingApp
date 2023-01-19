package com.padcmyanmar.smtz.themoviebookingapp.viewholders

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SnackQuantityButtonDelegate
import kotlinx.android.synthetic.main.view_holder_combo_set.view.*

class ComboSetViewHolder(itemView: View, private var mDelegate: SnackQuantityButtonDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mSnack : SnackVO? = null

    init {
        itemView.btnAddButton.setOnClickListener {
            mSnack?.let { snack->
                mDelegate.onTapAddQuantity(snack.id)
            }
        }
        itemView.btnRemoveButton.setOnClickListener {
            mSnack?.let { snack->
                mDelegate.onTapRemoveQuantity(snack.id)
            }
        }
    }

    fun bindNewData(snack: SnackVO) {
        mSnack = snack

        itemView.tvComboSet.text = snack.name
        itemView.tvComboSetComponents.text = snack.description
        itemView.tvTotal.text = "${snack.price}$"
        itemView.tvSnackQuantity.text = snack.quantity.toString()


    }
}