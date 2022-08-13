package com.example.exerciseday_android.ui.find

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.databinding.ActivityFindIdResultBinding
import com.example.exerciseday_android.ui.login.LoginActivity

class FindIdResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityFindIdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindIdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultBackBtn.setOnClickListener {
            finish()
        }

        binding.resultIdTv.text = intent.getStringExtra("email")
        binding.resultCreateTv.text = "가입일 "+intent.getStringExtra("create").toString().replace("-",".")

        binding.resultLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.resultPwBtn.setOnClickListener {
//            val intent = Intent(this, findctivity::class.java)
//            startActivity(intent)
        }
    }

}