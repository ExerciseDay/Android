package com.example.exerciseday_android.ui.gym.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityGymImgListBinding
import com.example.exerciseday_android.databinding.ActivityJoinCompleteBinding

class GymImgListActivity : AppCompatActivity() {

    lateinit var binding: ActivityGymImgListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGymImgListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기
        binding.gymImgListBackBtn.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        // 이미지 클릭 시
        binding.gymImgListRepresentIv.setOnClickListener {

        }

        binding.gymImgListHealthFacilitiesIv.setOnClickListener {

        }

        binding.gymImgListHealthFacilitiesIv.setOnClickListener {

        }

    }
}