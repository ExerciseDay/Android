package com.example.exerciseday_android.ui.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exerciseday_android.databinding.ActivityJoinPhoneBinding
import com.example.exerciseday_android.databinding.ActivityPurposeSettingBinding

class PurposeSettingActivity : AppCompatActivity() {

    lateinit var binding: ActivityPurposeSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurposeSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}