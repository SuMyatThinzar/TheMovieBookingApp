package com.padcmyanmar.smtz.themoviebookingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.smtz.themoviebookingapp.fragments.LoginFragment
import com.padcmyanmar.smtz.themoviebookingapp.fragments.SignInFragment

class AccountViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> LoginFragment()
            else -> SignInFragment()
        }
    }
}