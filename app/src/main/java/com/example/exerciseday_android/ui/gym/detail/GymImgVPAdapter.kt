package com.example.exerciseday_android.ui.gym.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class GymImgVPAdapter(
    childFragmentManager: FragmentManager,
    getLifecycle: Lifecycle
) :
    FragmentStateAdapter(childFragmentManager, getLifecycle) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size - 1)
    }
}