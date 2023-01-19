package com.padcmyanmar.smtz.themoviebookingapp.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.delegates.AccountButtonActionDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.LogInFragmentToActivityDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewpods.AccountButtonViewPod
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.view_pod_account.*

class LoginFragment : Fragment() {

    lateinit var mDelegate: AccountButtonActionDelegate
    lateinit var accountButtonViewPod: AccountButtonViewPod
    lateinit var mDelegateLogin: LogInFragmentToActivityDelegate

    lateinit var email: String
    lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    //1
    override fun onAttach(context: Context) {
        mDelegate = context as AccountButtonActionDelegate
        mDelegateLogin = context as LogInFragmentToActivityDelegate
        super.onAttach(context)
    }

    //2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        accountButtonViewPod = vpLogin as AccountButtonViewPod
        accountButtonViewPod.setDelegateViewPod(mDelegate)
        //accountButtonViewPod.setDelegateDataSignIn(mDelegateLogin)

        btnConfirm.setOnClickListener {

            email = etLoginEmail.text.toString()
            password = etLoginPassword.text.toString()

            if(email.isEmpty()){
                etLoginEmail.error = "Email required"
                etLoginEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                etLoginPassword.error = "Password required"
                etLoginPassword.requestFocus()
                return@setOnClickListener
            }

            //requestLoginData()
            //AccountActivity().requestLoginData(email, password)

//            mDelegate.onTapConfirm()
            mDelegateLogin.onSendDataLogIn(email, password)
        }

        super.onViewCreated(view, savedInstanceState)
    }

//    private fun requestLoginData() {
//        mTheMovieBookingModel.loginUser(
//            email = email,
//            password = password,
//            onSuccess = {
//                Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
//            },
//            onFailure = {
//                Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
//            }
//        )
//    }

}