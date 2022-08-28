package com.example.exerciseday_android.ui.course.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.databinding.FragmentSearchTypeBinding

class SearchTypeFragment : Fragment() {
    lateinit var binding: FragmentSearchTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchTypeBinding.inflate(inflater, container, false)

        return binding.root
    }
}