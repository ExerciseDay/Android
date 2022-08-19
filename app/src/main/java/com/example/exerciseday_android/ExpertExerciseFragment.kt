package com.example.exerciseday_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exerciseday_android.databinding.FragmentExpertExerciseBinding
import com.example.exerciseday_android.databinding.FragmentSelectBodyDetailPartBinding


class ExpertExerciseFragment : Fragment() {

    lateinit var binding: FragmentExpertExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpertExerciseBinding.inflate(inflater, container, false)




        return binding.root
    }


}