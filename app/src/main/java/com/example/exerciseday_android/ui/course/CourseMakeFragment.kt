package com.example.exerciseday_android.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.HomeFragment
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.databinding.FragmentCourseMakeBinding
import com.example.exerciseday_android.ui.course.custom.CustomStartFragment
import com.example.exerciseday_android.ui.course.expert.SelectBodyPartFragment

class CourseMakeFragment : Fragment() {
    lateinit var binding: FragmentCourseMakeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseMakeBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

        binding.courseMakeBackBtn.setOnClickListener {
            val homeFragment = HomeFragment()
            mainActivity.replaceFragment(homeFragment)
        }

        binding.courseMakeCustomBtn.setOnClickListener {
            val customStartFragment = CustomStartFragment()
            mainActivity.replaceFragment(customStartFragment)
        }

        binding.courseMakeExpertBtn.setOnClickListener {
            val selectBodyPartFragment = SelectBodyPartFragment()
            mainActivity.replaceFragment(selectBodyPartFragment)
        }

        return binding.root
    }
}