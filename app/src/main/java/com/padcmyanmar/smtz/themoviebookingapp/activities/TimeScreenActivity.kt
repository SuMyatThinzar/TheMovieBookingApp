package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.adapters.DateViewHolderAdapter
import com.padcmyanmar.smtz.themoviebookingapp.adapters.HostCityAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CinemaVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.TimeScreenDateVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeScreenDateButtonDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.TimeslotDelegate
import kotlinx.android.synthetic.main.activity_time_screen.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TimeScreenActivity : AppCompatActivity(), TimeScreenDateButtonDelegate, TimeslotDelegate {

    lateinit var mDateAdapter: DateViewHolderAdapter
    lateinit var mTimeScreenHostAdapter: HostCityAdapter

    private var mTwoWeeksDateList = mutableListOf<TimeScreenDateVO>()

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl

    private var mMovieId: Int? = null
    private var mMovieName: String? = null
    private var mSelectedDate: String? = null                 //2022-08-29      for network call
    private var mSelectedDateInstant: String? = null          // Saturday       for seat date
    private var mSelectedCinema: String? = null
    private var mCinemaList : List<CinemaVO> = listOf()
    private var mSelectedTimeslot: String? = null
    private var mTimeslotId: Int = 0
    private var mCinemaId: Int = 0

    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA MOVIE ID"
        private const val EXTRA_MOVIE_NAME = "EXTRA MOVIE NAME"

        fun newIntent(context: Context, movieId: Int, mMovieName: String?): Intent {
            val intent = Intent(context, TimeScreenActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, mMovieName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_screen)

        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieName = intent.getStringExtra(EXTRA_MOVIE_NAME)

        setUpDateAdapter()
        setUpTimeCityAdapter()
        setUpOnClickListener()

        setDate()
        getCinemaDate()

    }

    //Make first date to show
    private fun getCinemaDate() {
        val date = mTwoWeeksDateList.firstOrNull()?.date
        mSelectedDate = date
        mTwoWeeksDateList.firstOrNull()?.isSelected = true
        mMovieId?.let {
            requestCinemaData(it, date!!)
        }
    }

    private fun setUpOnClickListener() {
        btnTimeScreenNext.setOnClickListener {
            mSelectedDateInstant?.let{
                startActivity(SeatingPlanActivity.newIntent(this, mMovieName, mSelectedCinema, mSelectedDateInstant, mSelectedDate, mSelectedTimeslot, mTimeslotId, mMovieId, mCinemaId))
            }
        }
        btnTimeScreenBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpDateAdapter() {

        mDateAdapter = DateViewHolderAdapter(this)
        rvDate.adapter = mDateAdapter
        rvDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }
    private fun setUpTimeCityAdapter() {

        mTimeScreenHostAdapter = HostCityAdapter(this)
        rvHostCity.adapter = mTimeScreenHostAdapter
        rvHostCity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun setDate() {
        val twoWeeksDate = mutableListOf<TimeScreenDateVO>()

        for (day in 0..14) {

            val calendar: Calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, day)
            val dateFormat = SimpleDateFormat("EEE", Locale.ENGLISH).format(calendar.time)
            val dayFormat = SimpleDateFormat("dd", Locale.ENGLISH).format(calendar.time)
            val dateInstantForBooking = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
            val dateFormatFull = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.time)

            twoWeeksDate.add(
                TimeScreenDateVO(
                    date = dateFormat,
                    day = dayFormat,
                    dateFull = dateFormatFull,
                    dateInstant = dateInstantForBooking
                )
            )
        }
        mTwoWeeksDateList = twoWeeksDate
        mDateAdapter.bindData(twoWeeksDate)

    }

    //Cinema Host SetUp
    private fun requestCinemaData(movieId: Int, date: String) {
        mTheMovieBookingModel.getCinemaDayTimeSlot(
            movieId = movieId.toString(),
            date = date,
            onSuccess = { cinemaList ->
                mTimeScreenHostAdapter.bindData(cinemaList)
                mCinemaList = cinemaList
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    //Delegate Date Listener
    override fun onTapDatePicker(selectedDate: String) {
        mSelectedDate = selectedDate
        //Toast.makeText(this, "$mSelectedDate", Toast.LENGTH_SHORT).show()

        mTwoWeeksDateList.forEach {
            if(it.dateFull == selectedDate){
                it.isSelected = true
                mSelectedDateInstant = it.dateInstant
                mDateAdapter.bindData(mTwoWeeksDateList)
            } else {
                it.isSelected = false
            }
        }
        mMovieId?.let {
            requestCinemaData(it, selectedDate)
        }
    }

    // child
    override fun onTapTimeslot(timeslotId: Int) {

        mTimeslotId = timeslotId

        for(cinema in mCinemaList){
            for(timeslot in cinema.timeslots ?: listOf()){
                if(timeslot.cinemaDayTimeslotId == timeslotId) {
                    timeslot.isSelected = true
                    mTimeScreenHostAdapter.bindData(mCinemaList)
                    mSelectedTimeslot = timeslot.startTime
                    mSelectedCinema = cinema.cinema
                } else {
                    timeslot.isSelected = false
                }
            }
            mCinemaId = cinema.cinemaId?:0
        }
    }
}