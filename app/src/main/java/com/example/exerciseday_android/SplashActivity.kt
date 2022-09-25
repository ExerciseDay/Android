package com.example.exerciseday_android

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.example.exerciseday_android.databinding.ActivitySplashBinding
import com.example.exerciseday_android.ui.login.LoginAccessActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상태바 설정
        val windowController = WindowInsetsControllerCompat(this.window, this.window.decorView)
        windowController.isAppearanceLightStatusBars = false

        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        Handler().postDelayed({
            val intent = Intent(this, LoginAccessActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 3000)
    }
}