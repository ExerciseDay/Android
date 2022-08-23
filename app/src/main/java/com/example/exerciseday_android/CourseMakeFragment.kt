package com.example.exerciseday_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.databinding.FragmentCourseMakeBinding

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

        return binding.root
    }
}