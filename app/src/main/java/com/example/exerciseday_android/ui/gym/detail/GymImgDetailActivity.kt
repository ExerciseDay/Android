package com.example.exerciseday_android.ui.gym.detail

import android.R
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.exerciseday_android.databinding.ActivityGymImgDetailBinding


class GymImgDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityGymImgDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGymImgDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상태바 설정
        window.statusBarColor = (binding.root.background as ColorDrawable).color

        val windowController = WindowInsetsControllerCompat(this.window, this.window.decorView)
        windowController.isAppearanceLightStatusBars = false





        // 닫기 버튼 클릭 시
        binding.gymImgDetailCloseBtn.setOnClickListener {
            finish()
        }

    }
}