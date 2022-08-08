package com.example.exerciseday_android.ui.join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityJoinCompleteBinding
import com.example.exerciseday_android.databinding.ActivityPurposeSettingBinding
import com.example.exerciseday_android.ui.login.LoginActivity

class JoinCompleteActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityJoinCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 뒤로 가기
        binding.joinCompleteBackBtn.setOnClickListener(this)

        // 확인 버튼 클릭 시
        binding.joinCompleteCheckBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.join_complete_back_btn -> finish()
            R.id.join_complete_check_btn -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }
}