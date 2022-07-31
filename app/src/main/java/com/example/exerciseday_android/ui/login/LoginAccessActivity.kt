package com.example.exerciseday_android.ui.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.exerciseday_android.databinding.ActivityLoginAccessBinding

class LoginAccessActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginAccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginAccessCheckBtn.setOnClickListener {

        }
    }
}