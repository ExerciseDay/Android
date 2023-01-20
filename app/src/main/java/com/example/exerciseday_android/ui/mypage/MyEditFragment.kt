package com.example.exerciseday_android.ui.mypage

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.databinding.FragmentMyProfileEditBinding


class MyEditFragment : Fragment() {

    lateinit var binding: FragmentMyProfileEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileEditBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

        binding.myNicknameEditBtn.setOnClickListener {
            binding.myNicknameEditBtn.visibility = View.INVISIBLE
            binding.myNicknameEditTv.visibility = View.INVISIBLE
            binding.myNicknameCompleteBtn.visibility = View.VISIBLE
            binding.myNicknameCompleteTv.visibility = View.VISIBLE
            binding.myNicknameEt.inputType = InputType.TYPE_CLASS_TEXT
            binding.myNicknameEt.isFocusable = true
        }

        binding.myNicknameCompleteBtn.setOnClickListener {
            binding.myNicknameEditBtn.visibility = View.VISIBLE
            binding.myNicknameEditTv.visibility = View.VISIBLE
            binding.myNicknameCompleteBtn.visibility = View.INVISIBLE
            binding.myNicknameCompleteTv.visibility = View.INVISIBLE
            binding.myNicknameEt.inputType = InputType.TYPE_NULL
            binding.myNicknameEt.isFocusable = false
        }

        binding.myBackBtn.setOnClickListener {
            val myPageFragment = MyPageFragment()
            mainActivity.replaceFragment(myPageFragment)
        }

        return binding.root
    }
}