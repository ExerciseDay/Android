package com.example.exerciseday_android.ui.gym.detail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentGymDetailBinding
import com.example.exerciseday_android.databinding.FragmentGymImgBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class GymImgFragment(private val imgRes: Int) : Fragment() {

    lateinit var binding: FragmentGymImgBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymImgBinding.inflate(inflater, container, false)

        // 헬스장 이미지 클릭 시
        binding.gymImgIv.setOnClickListener {
            val intent = Intent(activity, GymImgListActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.fade_in, R.anim.none)
        }

        binding.gymImgIv.setImageResource(imgRes)

        return binding.root
    }

}