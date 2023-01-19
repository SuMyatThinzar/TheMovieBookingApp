package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.adapters.MovieSeatAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.SEAT_TYPE_AVAILABLE
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieSeatDelegate
import kotlinx.android.synthetic.main.activity_seating_plan.*

class SeatingPlanActivity : AppCompatActivity(), MovieSeatDelegate {

    private lateinit var mMovieSeatAdapter : MovieSeatAdapter

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl
    private var mMovieSeatList: List<MovieSeatVO>? = null
    private var mSelectedSeat: String = ""                        //to checkout
    private var numberOfTicket = 0
    private var mTotalPrice = 0                                   //to checkout

    private var mMovieName: String? = null
    lateinit var mSelectedDateInstant: String
    lateinit var mSelectedDate: String                           //to checkout
    private var mTimeslot: String? = null
    private var mTimeslotId: Int = 0                             //to checkout
    private var mSelectedCinema: String? = null
    private var mRow: String = ""                                //to checkout
    private var mMovieId: Int? = 0
    private var mCinemaId: Int = 0

    companion object {

        private const val EXTRA_DATE = "EXTRA DATE"
        private const val EXTRA_MOVIE_NAME = "EXTRA MOVIE NAME"
        private const val EXTRA_TIME = "EXTRA TIME"
        private const val EXTRA_TIMESLOT_ID = "EXTRA TIMESLOT"
        private const val EXTRA_CINEMA = "EXTRA CINEMA"
        private const val EXTRA_SELECTED_DATE = "EXTRA SELECTED DATE"
        private const val EXTRA_MOVIE_ID = "EXTRA MOVIE ID"
        private const val EXTRA_CINEMA_ID = "EXTRA CINEMA ID"

        fun newIntent(
            context: Context,
            movieName: String?,
            cinema: String?,
            dateInstant: String?,                        //for this activity
            selectedDate: String?,
            time: String?,
            timeslotId: Int?,
            movieId: Int?,
            cinemaId: Int?
        ): Intent {
            val intent = Intent(context, SeatingPlanActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_DATE, dateInstant)                               //for this activity
            intent.putExtra(EXTRA_SELECTED_DATE, selectedDate)
            intent.putExtra(EXTRA_TIME, time)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            intent.putExtra(EXTRA_CINEMA, cinema)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)

        mMovieName = intent.getStringExtra(SeatingPlanActivity.EXTRA_MOVIE_NAME)
        mTimeslot = intent.getStringExtra(SeatingPlanActivity.EXTRA_TIME)
        mTimeslotId = intent.getIntExtra(SeatingPlanActivity.EXTRA_TIMESLOT_ID, 0)         //to checkout
        mSelectedDateInstant = intent.getStringExtra(EXTRA_DATE).toString()                          //for this activity
        mSelectedDate = intent.getStringExtra(EXTRA_SELECTED_DATE).toString()                        //to checkout
        mSelectedCinema = intent.getStringExtra(EXTRA_CINEMA)
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mCinemaId = intent.getIntExtra(EXTRA_CINEMA_ID, 0)

        setUpAdapters()
        setUpListeners()

        setUpViewWithData(mMovieName?:"", mSelectedDate, mTimeslot?:"", mSelectedCinema?:"")
        requestData(mSelectedDateInstant?:"", mTimeslotId)

//        mCarriedData = Gson().fromJson(mData, CarriedVO::class.java)
//        mCarriedData?.let{
//            requestData(it)
//            setUpViewWithData(it)
//        }
    }

    private fun setUpViewWithData(movieName: String, date: String, timeslot: String, cinema:String) {
        tvMovieNameSeatingPlan.text = movieName
        tvTheaterSeatingPlan.text = cinema
        tvDateSeatingPlan.text = "${date}, ${timeslot}"
    }

    private fun requestData(date: String, timeslotId: Int){
       mTheMovieBookingModel.getSeatingPlan(
            bookingDate = date,
            cinemaDayTimeslotId = timeslotId.toString(),
            onSuccess = {
                mMovieSeatList = it
                mMovieSeatAdapter.setNewData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpAdapters(){

        mMovieSeatAdapter = MovieSeatAdapter(mDelegate = this)
        rvSeatingPlan.adapter = mMovieSeatAdapter
        rvSeatingPlan.layoutManager = GridLayoutManager(applicationContext, 14)

        //mMovieSeatAdapter.setNewData(DUMMY_SEATS)
    }

    private fun setUpListeners(){
        btnSeatingPlanBack.setOnClickListener {
            super.onBackPressed()
        }
        btnseatingPlanConfirm.setOnClickListener {
//            Toast.makeText(applicationContext,mTotalPrice,Toast.LENGTH_SHORT).show()
            startActivity(SnackScreenActivity.newIntent(this, totalPrice = mTotalPrice, timeslotId = mTimeslotId, row = mRow, selectedSeat = mSelectedSeat, selectedDate = mSelectedDate, dateInstant = mSelectedDateInstant, movieId = mMovieId, cinemaId = mCinemaId))

        }
    }

    override fun onTapMovieSeat(seatName: String) {
        for(seat in mMovieSeatList ?: listOf()){
            if(seat.seatName == seatName){
                if(seat.type == SEAT_TYPE_AVAILABLE){
                    if(seat.isSelected == false){
                        seat.isSelected = true
                        mMovieSeatAdapter.setNewData(mMovieSeatList ?: listOf())

                        numberOfTicket ++

                        if(numberOfTicket >= 2){
                            mSelectedSeat = mSelectedSeat.plus(","+seat.seatName)
                        }else{
                            mSelectedSeat = mSelectedSeat.plus(seat.seatName)
                        }
                        mRow = seat.symbol
                        mTotalPrice += seat.price.toInt()
                    }
                    else{
                        seat.isSelected = false
                        mMovieSeatAdapter.setNewData(mMovieSeatList ?: listOf())
//                        mSelectedSeat = mSelectedSeat.replace(seat.seatName, "")
                        numberOfTicket --

                        mSelectedSeat = mSelectedSeat.replace(seat.seatName, "")

                        /*if(numberOfTicket >= 2){
                            val firstSelectedSeat = mSelectedSeat.substring(0,3)
                            val otherSeatFirstIndex = mSelectedSeat.indexOf(seat.seatName)
                            val otherSeat = mSelectedSeat.substring(otherSeatFirstIndex-1,otherSeatFirstIndex+3)

                            if(seatName == firstSelectedSeat){
                                mSelectedSeat = mSelectedSeat.substring(3)
                            } else {
                                mSelectedSeat = mSelectedSeat.substring(otherSeatFirstIndex, mSelectedSeat.length)
                            }
                        }*/

                        mTotalPrice -= seat.price.toInt()
                    }
                }
            }
        }

        tvSelectedTickets.text = numberOfTicket.toString()
        tvSelectedSeats.text = mSelectedSeat
        btnseatingPlanConfirm.text = "Buy Ticket for $$mTotalPrice"
    }
}