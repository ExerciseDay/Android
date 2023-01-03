package com.example.exerciseday_android.ui.terms

import com.example.exerciseday_android.databinding.ActivityTermPersonalBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TermPersonalActivity : AppCompatActivity() {
    lateinit var binding: ActivityTermPersonalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.termPersonalBackBtn.setOnClickListener {
            finish()
        }
    }
}