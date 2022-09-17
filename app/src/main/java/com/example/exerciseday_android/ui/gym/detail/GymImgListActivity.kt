package com.example.exerciseday_android.ui.gym.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityGymImgListBinding
import com.example.exerciseday_android.databinding.ActivityJoinCompleteBinding
import com.example.exerciseday_android.ui.join.JoinInfoActivity

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
            val intent = Intent(this, GymImgDetailActivity::class.java)
            startActivity(intent)
        }

        binding.gymImgListHealthFacilitiesVp.setOnClickListener {
            val intent = Intent(this, GymImgDetailActivity::class.java)
            startActivity(intent)
        }

        binding.gymImgListCommonFacilitiesVp.setOnClickListener {
            val intent = Intent(this, GymImgDetailActivity::class.java)
            startActivity(intent)
        }


        //TDL
        // 이미지 넘기기 버튼 설정

    }
}