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
import androidx.viewpager2.widget.ViewPager2
import com.example.exerciseday_android.databinding.ActivityGymImgDetailBinding


class GymImgDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityGymImgDetailBinding
    private var currentImgIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGymImgDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상태바 설정
        window.statusBarColor = (binding.root.background as ColorDrawable).color
        val windowController = WindowInsetsControllerCompat(this.window, this.window.decorView)
        windowController.isAppearanceLightStatusBars = false


        // ViewPager2
        val gymImgDetailFm = supportFragmentManager
        val gymImgDetailLifecycle = lifecycle
        val gymImgDetailVPAdapter =
            GymImgVPAdapter(gymImgDetailFm, gymImgDetailLifecycle)

        // 헬스장 이미지 더미데이터
        gymImgDetailVPAdapter.addFragment(GymImgFragment(com.example.exerciseday_android.R.drawable.ex_gym_img))
        gymImgDetailVPAdapter.addFragment(GymImgFragment(com.example.exerciseday_android.R.drawable.temp))
        gymImgDetailVPAdapter.addFragment(GymImgFragment(com.example.exerciseday_android.R.drawable.ex_gym_img))

        binding.gymImgDetailVp.adapter = gymImgDetailVPAdapter


        // 초기 설정
        currentImgIndex = 0
        binding.gymImgDetailImgPrevBtn.visibility = View.GONE
        binding.gymImgDetailImgNextBtn.visibility = View.VISIBLE


        // 이미지 넘기기 버튼 기능
        binding.gymImgDetailImgNextBtn.setOnClickListener {
            currentImgIndex++
            binding.gymImgDetailVp.currentItem = currentImgIndex

            if (currentImgIndex == gymImgDetailVPAdapter.itemCount - 1) {
                binding.gymImgDetailImgNextBtn.visibility = View.GONE
                binding.gymImgDetailImgPrevBtn.visibility = View.VISIBLE
            } else {
                binding.gymImgDetailImgPrevBtn.visibility = View.VISIBLE
                binding.gymImgDetailImgNextBtn.visibility = View.VISIBLE
            }
        }

        binding.gymImgDetailImgPrevBtn.setOnClickListener {
            currentImgIndex--
            binding.gymImgDetailVp.currentItem = currentImgIndex

            if (currentImgIndex == 0) {
                binding.gymImgDetailImgPrevBtn.visibility = View.GONE
                binding.gymImgDetailImgNextBtn.visibility = View.VISIBLE
            } else {
                binding.gymImgDetailImgPrevBtn.visibility = View.VISIBLE
                binding.gymImgDetailImgNextBtn.visibility = View.VISIBLE
            }
        }


        binding.gymImgDetailVp.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                currentImgIndex = position

                when (position) {
                    0 -> {
                        // First Page
                        binding.gymImgDetailImgPrevBtn.visibility = View.GONE
                        binding.gymImgDetailImgNextBtn.visibility = View.VISIBLE
                    }
                    gymImgDetailVPAdapter.itemCount - 1 -> {
                        // Last Page
                        binding.gymImgDetailImgNextBtn.visibility = View.GONE
                        binding.gymImgDetailImgPrevBtn.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.gymImgDetailImgPrevBtn.visibility = View.VISIBLE
                        binding.gymImgDetailImgNextBtn.visibility = View.VISIBLE
                    }
                }
            }
        })


        // 닫기 버튼 클릭 시
        binding.gymImgDetailCloseBtn.setOnClickListener {
            finish()
        }

    }
}