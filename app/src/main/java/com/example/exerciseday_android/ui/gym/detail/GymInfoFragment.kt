package com.example.exerciseday_android.ui.gym.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exerciseday_android.databinding.FragmentGymInfoBinding


class GymInfoFragment : Fragment() {

    lateinit var binding: FragmentGymInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymInfoBinding.inflate(inflater, container, false)

        binding.gymInfoStarRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.gymInfoStarPointTv.text = rating.toString()
        }

        return binding.root
    }


}