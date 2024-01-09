package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.UserDataVO
import com.padcmyanmar.smtz.themoviebookingapp.delegates.MovieDetailDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewpods.MovieViewPod
import kotlinx.android.synthetic.main.activity_home_screen.*
import okhttp3.internal.toImmutableList

class HomeScreenActivity : AppCompatActivity(), MovieDetailDelegate {

    lateinit var nowShowingMovieViewPod: MovieViewPod
    lateinit var comingSoonMovieViewPod: MovieViewPod

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl

    private var mMovieList : List<MovieVO> = listOf()

    companion object {

        fun newIntent(activity: Activity): Intent {
            return Intent(activity, HomeScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setUpToolBar()
        setUpMovieViewPod()
        setUpDelegateViewPod()
        setUpListeners()

        requestData()
    }

    private fun requestData() {
        mTheMovieBookingModel.getProfile(
            onSuccess = {
                setNewData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
        mTheMovieBookingModel.getNowPlayingMovies(
            onSuccess = {
                mMovieList = it
                nowShowingMovieViewPod.setData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
        mTheMovieBookingModel.getComingSoonMovies(
            onSuccess = {
                comingSoonMovieViewPod.setData(it)
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setNewData(data: UserDataVO) {

        tvProfile.text = "Hi! ${data.name}"
        tvProfileName.text = data.name
        tvEmail.text = data.email
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = ""

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        toggle.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    //Changing Text for ViewPod, not concern Delegate
    private fun setUpMovieViewPod() {
        nowShowingMovieViewPod = vpNowShowing as MovieViewPod
        nowShowingMovieViewPod.setUpMovieViewPod(getString(R.string.lbl_now_showing))

        comingSoonMovieViewPod = vpComingSoon as MovieViewPod
        comingSoonMovieViewPod.setUpMovieViewPod(getString(R.string.lbl_coming_soon))
    }

    //Delegate to Detail Screen
    private fun setUpDelegateViewPod() {
        nowShowingMovieViewPod = vpNowShowing as MovieViewPod
        nowShowingMovieViewPod.setUpDelegateViewPod(this)

        comingSoonMovieViewPod = vpComingSoon as MovieViewPod
        comingSoonMovieViewPod.setUpDelegateViewPod(this)
    }

    private fun setUpListeners() {
        llLogout.setOnClickListener {
//            AccountActivity().requestDataLogOut()
            mTheMovieBookingModel.logoutUser(
                onSuccess = {
                    startActivity(Intent(this, AccountActivity::class.java))
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                },
                onFailure = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

}