package com.example.exerciseday_android.ui.terms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.databinding.ActivityTermLocationBinding

class TermLocationActivity : AppCompatActivity() {
    lateinit var binding: ActivityTermLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.locationConditionBackBtn.setOnClickListener {
            finish()
        }
    }
}