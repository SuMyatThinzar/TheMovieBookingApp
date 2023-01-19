import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.CardIdDelegate

class CarouselAdapter(private var mDelegate: CardIdDelegate) : RecyclerView.Adapter<CardViewHolder>() {

    private var mCardList: List<CardVO> = listOf()

    override fun getItemCount() = mCardList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_card, parent, false)
        return CardViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        if(mCardList.isNotEmpty()){
            holder.bindNewData(mCardList[position])
        }
    }

    fun bindData(cardList: List<CardVO>) {
        mCardList = cardList
        mCardList = mCardList.reversed()
        notifyDataSetChanged()
    }

    fun getCurrentPosition() : Int{
        return 0
    }

}