package com.example.exerciseday_android

import android.app.appsearch.SearchResult
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.databinding.FragmentCustomOptionBinding
import com.example.exerciseday_android.ui.ChangeDialog

class CustomOptionFragment : Fragment() {
    lateinit var binding: FragmentCustomOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomOptionBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity
        val prevFragment = CustomExerciseFragment()
        val nextFragment = SearchResultFragment()

        binding.customOptionBackBtn.setOnClickListener {
            mainActivity.replaceFragment(prevFragment)
        }

        binding.customOptionGetBtn.setOnClickListener {
            val dialog = ChangeDialog(mainActivity)
            dialog.show("추가되셨습니다!")
            dialog.btnClickListener {
                mainActivity.replaceFragment(nextFragment)
            }
        }

        return binding.root
    }
}