package com.example.exerciseday_android.ui.course.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.databinding.FragmentSearchLateBinding

class SearchLateFragment : Fragment() {
    lateinit var binding: FragmentSearchLateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchLateBinding.inflate(inflater, container, false)

        return binding.root
    }
}