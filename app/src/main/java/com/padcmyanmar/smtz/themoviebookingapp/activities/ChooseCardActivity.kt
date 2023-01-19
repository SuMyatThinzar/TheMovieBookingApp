package com.padcmyanmar.smtz.themoviebookingapp.activities

import CarouselAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CheckoutRequestVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CheckoutVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.CardIdDelegate
import kotlinx.android.synthetic.main.activity_chose_card.*

class ChooseCardActivity : AppCompatActivity(), CardIdDelegate {

    lateinit var mCarouselAdapter: CarouselAdapter

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl
    private var mTotalPrice = 0

    private var mCardList : List<CardVO> = listOf()
    private var mCheckOutVO: CheckoutVO? = null
    private var mTimeslotId: Int = 0
    lateinit var mSelectedDate: String
    lateinit var mSelectedDateInstant: String
    lateinit var mSelectedSeat: String
    lateinit var mRow: String
    private var mCardId: Int = 0
    private var mMovieId: Int = 0
    private var mCinemaId: Int = 0
    private var mSnackList: ArrayList<SnackVO> = arrayListOf()

    companion object {

        private const val EXTRA_DATE = "EXTRA DATE"
        private const val EXTRA_TOTAL_PRICE = "EXTRA TOTAL PRICE"
        private const val EXTRA_TIMESLOT_ID = "EXTRA TIMESLOT"
        private const val EXTRA_SELECTED_DATE = "EXTRA SELECTED DATE"
        private const val EXTRA_SELECTED_SEAT = "EXTRA SELECTED SEAT"
        private const val EXTRA_SELECTED_ROW = "EXTRA SELECTED ROW"
        private const val EXTRA_MOVIE_ID = "EXTRA MOVIE ID"
        private const val EXTRA_CINEMA_ID = "EXTRA CINEMA ID"
        private const val EXTRA_SNACK_LIST = "EXTRA SNACK LIST"

        fun newIntent(
            context: Context,
            totalPrice: Int,
            timeslotId: Int,
            row: String,
            selectedSeat: String,
            selectedDate: String,
            dateInstant: String,
            movieId: Int?,
            cinemaId: Int?,
            snackList: ArrayList<SnackVO>?
        ): Intent {
            val intent = Intent(context, ChooseCardActivity::class.java)
            intent.putExtra(EXTRA_TOTAL_PRICE, totalPrice)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            intent.putExtra(EXTRA_SELECTED_DATE, selectedDate)
            intent.putExtra(EXTRA_DATE, dateInstant)
            intent.putExtra(EXTRA_SELECTED_SEAT, selectedSeat)
            intent.putExtra(EXTRA_SELECTED_ROW, row)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_SNACK_LIST, snackList)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_card)

        mTotalPrice = intent.getIntExtra(EXTRA_TOTAL_PRICE, 0)
        tvPaymentAmount.text = "$ ${mTotalPrice.toDouble().toString()}"

        mTimeslotId = intent.getIntExtra(EXTRA_TIMESLOT_ID, 0)
        mSelectedDate = intent.getStringExtra(EXTRA_SELECTED_DATE).toString()
        mSelectedDateInstant = intent.getStringExtra(EXTRA_DATE).toString()
        mSelectedSeat = intent.getStringExtra(EXTRA_SELECTED_SEAT).toString()
        mRow = intent.getStringExtra(EXTRA_SELECTED_ROW).toString()
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mCinemaId = intent.getIntExtra(EXTRA_CINEMA_ID, 0)
        mSnackList = intent.getParcelableArrayListExtra<SnackVO>(EXTRA_SNACK_LIST) as ArrayList<SnackVO> /* = java.util.ArrayList<com.padcmyanmar.smtz.themoviebookingapp.data.vos.SnackVO> */

        setUpCarouselAdapter()
        setUpListeners()

        requestData()
    }

    // bind cards at first
    private fun requestData() {
        mTheMovieBookingModel.getProfile(
            onSuccess = {
                it.cards?.let { cardList ->
                    mCardList = cardList
                    mCarouselAdapter.bindData(cardList)
                }
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpCarouselAdapter() {
//        mCarouselAdapter = CarouselAdapter(mDelegate = this)
//        vpCard.adapter = mCarouselAdapter

        mCarouselAdapter = CarouselAdapter(mDelegate = this)
        vpCard.adapter = mCarouselAdapter
        vpCard.apply {
            set3DItem(false)
            setAlpha(true)
            setInfinite(false)
            setIntervalRatio(0.75f)
        }
    }

    private fun setUpListeners() {
        btnChooseCardBack.setOnClickListener {
            super.onBackPressed()
        }
        btnChooseCardConfirm.setOnClickListener {

            val co = CheckoutRequestVO()
            co.cinemaDayTimeslotId = mTimeslotId
            co.row = mRow
            co.seatNumber = mSelectedSeat
            co.bookingDate = mSelectedDate
            co.totalPrice = mTotalPrice
            co.movieId = mMovieId
            co.cardId = mCardId
            co.cinemaId = mCinemaId
            co.snacks = mSnackList

            mTheMovieBookingModel.checkout(
                checkoutRequestVO = co,
                onSuccess = {
                    mCheckOutVO = it
                    val checkoutString = Gson().toJson(it, CheckoutVO::class.java)                  //toString
                    startActivity(VoucherActivity.newIntent(this,checkoutString, mTotalPrice))
                },
                onFailure = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()}
            )

        }
        btnAddNewCard.setOnClickListener {
            //1
            startActivityForResult(NewCardActivity.newIntent(this), 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        //4
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && intent != null) {
            requestData()
        }
    }

    override fun onTapCard(cardId: Int) {
        mCardId = cardId
    }
}