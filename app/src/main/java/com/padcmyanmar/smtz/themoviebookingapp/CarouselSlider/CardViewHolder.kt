import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.CardIdDelegate
import kotlinx.android.synthetic.main.view_item_card.view.*

class CardViewHolder(view: View, private var mDelegate: CardIdDelegate) : RecyclerView.ViewHolder(view) {

    private var mCard : CardVO? = null

    init {
        itemView.setOnClickListener{
            mCard?.let {
                mDelegate.onTapCard(it.id)
            }

        }
    }

    fun bindNewData(card: CardVO) {

        mCard = card

//        if(card != null)
//        var a = card.cardNumber?.substring(0,3)
//        var b = card.cardNumber?.substring(3,6)
//        var c = card.cardNumber?.substring(6,9)

//        Toast.makeText(itemView.context,"$a $b $c", Toast.LENGTH_SHORT).show()

        itemView.tvCarouselCardNumber.text = card.cardNumber
        itemView.tvCardHolderLabel.text = card.cardHolder
        itemView.tvCarouselExpireDate.text = card.expirationDate
    }
}