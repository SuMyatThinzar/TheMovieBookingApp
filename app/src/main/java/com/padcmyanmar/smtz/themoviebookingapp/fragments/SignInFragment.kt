package com.padcmyanmar.smtz.themoviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.padcmyanmar.smtz.themoviebookingapp.R
import com.padcmyanmar.smtz.themoviebookingapp.delegates.AccountButtonActionDelegate
import com.padcmyanmar.smtz.themoviebookingapp.delegates.SignInFragmentToActivityDelegate
import com.padcmyanmar.smtz.themoviebookingapp.viewpods.AccountButtonViewPod
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.view_pod_account.*

class SignInFragment : Fragment() {

    lateinit var mDelegate: AccountButtonActionDelegate
    lateinit var accountButtonViewPod: AccountButtonViewPod
    lateinit var mDelegateSignIn: SignInFragmentToActivityDelegate

    lateinit var name: String
    lateinit var email: String
    lateinit var phone: String
    lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    //1
    override fun onAttach(context: Context) {
        mDelegate = context as AccountButtonActionDelegate
        mDelegateSignIn = context as SignInFragmentToActivityDelegate
        super.onAttach(context)
    }

    //2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accountButtonViewPod = vpSignIn as AccountButtonViewPod
        accountButtonViewPod.setDelegateViewPod(mDelegate)

        btnConfirm.setOnClickListener {
            name = etSignInName.getText().toString()
            email = etSignInEmail.getText().toString()
            phone = etSignInPhoneNumber.getText().toString()
            password = etSignInPassword.getText().toString()

            if(email.isEmpty()){
                etSignInEmail.error = "Email required"
                etSignInEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                etSignInPassword.error = "Password required"
                etSignInPassword.requestFocus()
                return@setOnClickListener
            }
            if(name.isEmpty()){
                etSignInName.error = "Name required"
                etSignInName.requestFocus()
                return@setOnClickListener
            }
            if(phone.isEmpty()){
                etSignInPhoneNumber.error = "Phone required"
                etSignInPhoneNumber.requestFocus()
                return@setOnClickListener
            }

            //requestRegisterData()
//            AccountActivity().requestRegisterData(name, email, phone, password)

            //mDelegate.onTapConfirm()
            mDelegateSignIn.onSendDataSignIn(name, email, phone, password)
        }
        super.onViewCreated(view, savedInstanceState)
    }

//    private fun requestRegisterData() {
//        mTheMovieBookingModel.registerUser(
//            name = name,
//            email = email,
//            phone = phone,
//            password = password,
//            onSuccess = {
//                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//            },
//            onFailure = {
//                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//            })
//    }

}