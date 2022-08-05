package com.example.exerciseday_android.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.databinding.ActivityLoginAccessBinding
import com.example.exerciseday_android.ui.terms.TermLocationActivity

class LoginAccessActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginAccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginAccessCheckBtn.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        binding.loginAccessLocationBtn.setOnClickListener {
            val intent = Intent(this, TermLocationActivity::class.java)
            startActivity(intent)
        }
    }
}