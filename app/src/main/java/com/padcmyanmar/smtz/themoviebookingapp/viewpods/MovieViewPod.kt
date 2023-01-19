package com.padcmyanmar.smtz.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.smtz.themoviebookingapp.adapters.MovieViewPodAdapter
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieDetailDelegate
import kotlinx.android.synthetic.main.view_pod_movie.view.*

class MovieViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    lateinit var mMovieViewPodAdapter: MovieViewPodAdapter
    lateinit var mDelegate: MovieDetailDelegate

    override fun onFinishInflate() {
        //setUpViewPod()
        super.onFinishInflate()
    }

    //func to call from HomeScreenActivity
    fun setUpMovieViewPod(titleText: String){
        tvNowShowing.text = titleText
    }

    //func to call from HomeScreenActivity
    fun setUpDelegateViewPod(delegate: MovieDetailDelegate){
        setUpDelegate(delegate)
        setUpViewPod()
    }
    private fun setUpDelegate(delegate: MovieDetailDelegate){
        this.mDelegate = delegate
    }

    private fun setUpViewPod(){

        mMovieViewPodAdapter = MovieViewPodAdapter(mDelegate)
        rvMovie.adapter = mMovieViewPodAdapter
        rvMovie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    fun setData(movieList: List<MovieVO>) {
        mMovieViewPodAdapter.setNewData(movieList)
    }
}