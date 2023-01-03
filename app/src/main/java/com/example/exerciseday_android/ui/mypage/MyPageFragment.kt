package com.example.exerciseday_android.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

    lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        (context as MainActivity).supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        val mainActivity = activity as MainActivity

        binding.myEditBtn.setOnClickListener {
            val editFragment = MyEditFragment()
            mainActivity.replaceFragment(editFragment)
        }

        binding.mySettingBtn.setOnClickListener {
            val settingFragment = MySettingFragment()
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_in, R.anim.fade_out)
                .add(R.id.main_frm, settingFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        binding.myReviewBtn.setOnClickListener {
            val reviewFragment = MyReviewFragment()
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_in, R.anim.fade_out)
                .add(R.id.main_frm, reviewFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        binding.myLastGymBtn.setOnClickListener {
            val lastGymFragment = MyLastGymFragment()
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.slide_in, R.anim.fade_out)
                .add(R.id.main_frm, lastGymFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}