package com.padcmyanmar.smtz.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModel
import com.padcmyanmar.smtz.themoviebookingapp.data.models.TheMovieBookingModelImpl
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CheckoutVO
import com.padcmyanmar.smtz.themoviebookingapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.smtz.themoviebookingapp.utils.IMAGE_BASE_URL_2
import kotlinx.android.synthetic.main.activity_voucher.*
import kotlinx.android.synthetic.main.view_holder_cast.view.*

class VoucherActivity : AppCompatActivity() {

    private val mTheMovieBookingModel: TheMovieBookingModel = TheMovieBookingModelImpl
    private var mVoucherVO : CheckoutVO? = null
    private var mTotalPrice = 0

    companion object{
        const val EXTRA_CHECKOUTVO = "EXTRA CHECKOUTVO"
        private const val EXTRA_TOTAL_PRICE = "EXTRA TOTAL PRICE"

        fun newIntent(context: Context, checkoutVO: String, totalPrice: Int) : Intent{
            val intent = Intent(context, VoucherActivity::class.java)
            intent.putExtra(EXTRA_CHECKOUTVO, checkoutVO)
            intent.putExtra(EXTRA_TOTAL_PRICE, totalPrice)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        setUpListeners()

        mVoucherVO = Gson().fromJson(intent.getStringExtra(EXTRA_CHECKOUTVO), CheckoutVO::class.java)
        mTotalPrice = intent.getIntExtra(EXTRA_TOTAL_PRICE, 0)
        mVoucherVO?.let {
            requestData(it)
        }

    }

    private fun setUpListeners() {
        btnVoucherCancel.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
    }

    private fun requestData(voucherVO: CheckoutVO){

        mTheMovieBookingModel.getMovieDetails(
            movieId = voucherVO.movieId.toString(),
            onSuccess = {
                Glide.with(this)
                    .load("$IMAGE_BASE_URL_2${it.posterPath}")
                    .into(ivPoster)
                tvMovieNameVoucher.text = it.title?: ""
                tvMovieLengthVoucher.text = it.getMovieRunTime()
            },
            onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
        bindData(voucherVO)
    }

    private fun bindData(voucherVO: CheckoutVO){
        tvBookingNumber.text = voucherVO.bookingNo
        tvMovieShowTime.text = "${voucherVO.timeslot?.startTime ?: ""} : ${voucherVO.bookingDate}"
        val index = voucherVO.bookingNo.indexOf('-')
        tvTheater.text = voucherVO.bookingNo.substring(0, index)
        tvRowSymbol.text = voucherVO.row
        tvSeats.text = voucherVO.seat
        tvVoucherTotalPrice.text = "$${mTotalPrice.toDouble().toString()}"

        val barCode = BarcodeEncoder()
        val bitmap: Bitmap = barCode.encodeBitmap(voucherVO.bookingNo, BarcodeFormat.CODE_128, 200, 60)
        ivBarCode.setImageBitmap(bitmap)

    }
}