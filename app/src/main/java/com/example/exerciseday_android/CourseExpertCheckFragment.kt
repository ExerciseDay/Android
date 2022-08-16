package com.example.exerciseday_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exerciseday_android.databinding.FragmentCourseExpertCheckBinding


class CourseExpertCheckFragment : Fragment() {

    lateinit var binding: FragmentCourseExpertCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseExpertCheckBinding.inflate(inflater, container, false)




        return binding.root
    }

}