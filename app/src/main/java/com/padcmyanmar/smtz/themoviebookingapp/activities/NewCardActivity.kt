package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO
import kotlinx.android.synthetic.main.activity_new_card.*

class NewCardActivity : AppCompatActivity() {

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl

    private var mCardList : List<CardVO> = listOf()

    private var cardNumber: String? = null
    private var cardHolder: String? = null
    private var expirationDate: String? = null
    private var cvc: String? = null

    companion object {
        //2
        fun newIntent(context: Context): Intent {
            return Intent(context, NewCardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)

        setUpListeners()
    }

    private fun setUpListeners() {
        btnNewCardBack.setOnClickListener {
            super.onBackPressed()
        }
        btnNewCardConfirm.setOnClickListener {

            cardNumber = textInputCardNumber.text.toString()
            cardHolder = textInputCardHolder.text.toString()
            expirationDate = textInputExpirationDate.text.toString()
            cvc = textInputCVC.text.toString()

            requestData(cardNumber?:"" ,cardHolder?:"" ,expirationDate?:"" ,cvc?:"" )

//            setResult(Activity.RESULT_OK,intent)
//            finish()
        }

    }

    private fun requestData(cardNumber: String, cardHolder: String, expirationDate: String, cvc: String){
        mTheMovieBookingModel.createCard(
            cardNumber,
            cardHolder,
            expirationDate,
            cvc,
            onSuccess = { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                //3
                setResult(Activity.RESULT_OK,intent)
                finish()
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }
}