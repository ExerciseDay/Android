package com.example.exerciseday_android.ui.gym.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseday_android.databinding.FragmentGymReviewBinding
import com.google.android.material.tabs.TabLayoutMediator


class GymReviewFragment : Fragment() {

    lateinit var binding: FragmentGymReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymReviewBinding.inflate(inflater, container, false)




        return binding.root
    }



}