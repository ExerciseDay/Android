package com.example.exerciseday_android.ui.gym.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exerciseday_android.databinding.ActivityGymImgDetailBinding
import com.example.exerciseday_android.databinding.ActivityGymImgListBinding

class GymImgDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityGymImgDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGymImgDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}