package com.example.exerciseday_android.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

    lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

        binding.myEditBtn.setOnClickListener {
            val editFragment = MyEditFragment()
            mainActivity.replaceFragment(editFragment)
        }

        binding.mySettingBtn.setOnClickListener {
            val settingFragment = MySettingFragment()
            mainActivity.replaceFragment(settingFragment)
        }

        binding.myReviewBtn.setOnClickListener {
            val reviewFragment = MyReviewFragment()
            mainActivity.replaceFragment(reviewFragment)
        }

        binding.myLastGymBtn.setOnClickListener {
            val lastGymFragment = MyLastGymFragment()
            mainActivity.replaceFragment(lastGymFragment)
        }

        return binding.root
    }
}