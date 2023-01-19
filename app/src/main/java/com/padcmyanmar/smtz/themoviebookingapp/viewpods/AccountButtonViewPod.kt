package com.padcmyanmar.smtz.themoviebookingapp.viewpods

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat.startActivity
import com.padcmyanmar.smtz.themoviebookingapp.activities.HomeScreenActivity
import com.padcmyanmar.smtz.themoviebookingapp.adapters.AccountViewPagerAdapter
import com.padcmyanmar.smtz.themoviebookingapp.delegates.AccountButtonActionDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SignInFragmentToActivityDelegate
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_account.view.*
import kotlinx.android.synthetic.main.view_pod_account.*
import kotlinx.android.synthetic.main.view_pod_account.view.*

class AccountButtonViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    lateinit var mDelegate: AccountButtonActionDelegate

    fun setDelegateViewPod(mDelegate: AccountButtonActionDelegate) {
        this.mDelegate = mDelegate
    }

}