package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.adapters.ComboSetViewHolderAdapter
import com.padcmyanmar.smtz.themoviebookingapp.adapters.PaymentMethodViewHolderAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.CardTouchDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SnackQuantityButtonDelegate
import kotlinx.android.synthetic.main.activity_snack_screen.*

class SnackScreenActivity : AppCompatActivity(), SnackQuantityButtonDelegate, CardTouchDelegate {

    lateinit var mComboSetAdapter: ComboSetViewHolderAdapter
    lateinit var mPaymentMehodAdapter: PaymentMethodViewHolderAdapter

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl

    var mSnackList: ArrayList<SnackVO>? = ArrayList()
    private var mCardList: List<CardVO>? = listOf()
    private var mTimeslotId: Int = 0
    private var mTotalPrice = 0
    private var mSubTotal = 0
    lateinit var mSelectedDate: String
    lateinit var mSelectedDateInstant: String
    lateinit var mSelectedSeat: String
    lateinit var mRow: String
    private var mMovieId: Int? = 0
    private var mCinemaId: Int = 0

    companion object {

        private const val EXTRA_DATE = "EXTRA DATE"
        private const val EXTRA_TOTAL_PRICE = "EXTRA TOTAL PRICE"
        private const val EXTRA_TIMESLOT_ID = "EXTRA TIMESLOT"
        private const val EXTRA_SELECTED_DATE = "EXTRA SELECTED DATE"
        private const val EXTRA_SELECTED_SEAT = "EXTRA SELECTED SEAT"
        private const val EXTRA_SELECTED_ROW = "EXTRA SELECTED ROW"
        private const val EXTRA_MOVIE_ID = "EXTRA MOVIE ID"
        private const val EXTRA_CINEMA_ID = "EXTRA CINEMA ID"

        fun newIntent(
            context: Context,
            totalPrice: Int,
            timeslotId: Int,
            row: String,
            selectedSeat: String,
            selectedDate: String,
            dateInstant: String,
            movieId: Int?,
            cinemaId: Int?
        ): Intent {
            val intent = Intent(context, SnackScreenActivity::class.java)
            intent.putExtra(EXTRA_TOTAL_PRICE, totalPrice)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            intent.putExtra(EXTRA_DATE, dateInstant)
            intent.putExtra(EXTRA_SELECTED_DATE, selectedDate)
            intent.putExtra(EXTRA_SELECTED_SEAT, selectedSeat)
            intent.putExtra(EXTRA_SELECTED_ROW, row)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack_screen)

        setUpAdapter()
        setUpListener()

        mTotalPrice = intent.getIntExtra(EXTRA_TOTAL_PRICE, 0)
        btnSnackScreenPay.text = "Pay $${mTotalPrice.toDouble()}"

        mTimeslotId = intent.getIntExtra(EXTRA_TIMESLOT_ID, 0)
        mSelectedDate = intent.getStringExtra(EXTRA_SELECTED_DATE).toString()
        mSelectedDateInstant = intent.getStringExtra(EXTRA_DATE).toString()
        mSelectedSeat = intent.getStringExtra(EXTRA_SELECTED_SEAT).toString()
        mRow = intent.getStringExtra(EXTRA_SELECTED_ROW).toString()
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mCinemaId = intent.getIntExtra(EXTRA_CINEMA_ID, 0)

        requestData()

    }

    private fun requestData() {
        mTheMovieBookingModel.getSnack(
            onSuccess = {
                mSnackList = it
                mComboSetAdapter.bindData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )

        mTheMovieBookingModel.getPaymentMethod(
            onSuccess = {
                mCardList = it
                mPaymentMehodAdapter.bindData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListener() {
        btnSnackBarBack.setOnClickListener {
            super.onBackPressed()
        }
        btnSnackScreenPay.setOnClickListener {
            startActivity(ChooseCardActivity.newIntent(this, mTotalPrice, mTimeslotId, mRow,mSelectedSeat,mSelectedDate,mSelectedDateInstant,mMovieId,mCinemaId,mSnackList))
        }
    }

    private fun setUpAdapter() {
        mComboSetAdapter = ComboSetViewHolderAdapter(mDelegate = this)
        rvComboSet.adapter = mComboSetAdapter
        rvComboSet.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mPaymentMehodAdapter = PaymentMethodViewHolderAdapter(mDelegate = this)
        rvPaymentMethod.adapter = mPaymentMehodAdapter
        rvPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapAddQuantity(id: Int) {

        for (n in mSnackList ?: listOf()) {
            if (id == n.id) {
                n.quantity++
                mSubTotal += n.price?.toInt() ?: 0
                mTotalPrice += n.price?.toInt() ?: 0
                mSnackList?.let { mComboSetAdapter.bindData(it) }
                tvSubTotal.text = "Sub Total : ${mSubTotal.toString()}$"
                btnSnackScreenPay.text = "Pay $${mTotalPrice.toDouble()}"
            }
        }
    }

    override fun onTapRemoveQuantity(id: Int) {

        for (n in mSnackList ?: listOf()) {
            if (id == n.id) {
                if (n.quantity > 0) {
                    n.quantity--
                    mSubTotal -= n.price?.toInt() ?: 0
                    mTotalPrice -= n.price?.toInt() ?: 0
                    mSnackList?.let { mComboSetAdapter.bindData(it) }
                    tvSubTotal.text = "Sub Total : ${mSubTotal.toString()}$"
                    btnSnackScreenPay.text = "Pay $${mTotalPrice.toDouble()}"
                } else {
                    Toast.makeText(this, "Quantity is 0", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onTapCard(cardId: Int) {

        mCardList?.forEach {
            if (it.id == cardId) {
                it.isSelected = true
                mPaymentMehodAdapter.bindData(mCardList ?: listOf())
            } else {
                it.isSelected = false
                mPaymentMehodAdapter.bindData(mCardList ?: listOf())
            }
        }
    }
}