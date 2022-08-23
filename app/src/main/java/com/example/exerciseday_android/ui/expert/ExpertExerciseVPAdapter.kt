package com.example.exerciseday_android.ui.expert

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ExpertExerciseVPAdapter(childFragmentManager: FragmentManager, getLifecycle: Lifecycle) :
    FragmentStateAdapter(childFragmentManager, getLifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExpertExerciseOptionFragment()  // 추천 옵션 탭
            else -> ExpertExerciseWayFragment()  // 운동 방법 탭
        }
    }
}