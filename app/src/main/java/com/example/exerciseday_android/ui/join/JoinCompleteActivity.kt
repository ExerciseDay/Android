package com.example.exerciseday_android.ui.join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.exerciseday_android.data.remote.auth.AuthService
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.model.User
import com.example.exerciseday_android.databinding.ActivityJoinCompleteBinding
import com.example.exerciseday_android.ui.login.LoginActivity


class JoinCompleteActivity : AppCompatActivity(), JoinView, View.OnClickListener {

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
                join()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }

    private fun getUser(): User {
        // 가입정보 전달 받기
        val joinInfoList = intent.extras!!.getStringArrayList("join")

        val email: String = joinInfoList!![0]
        val nickname: String = joinInfoList[1]
        val password: String = joinInfoList[2]
        val phone: String = joinInfoList[3]
        val gender: String = joinInfoList[4]
        val goal: String = joinInfoList[5]

        Log.d("join_getUser", User(email, password, nickname, phone, gender, goal).toString())

        return User(email, password, nickname, phone, gender, goal)
    }

    private fun join() {
        val joinService = AuthService()
        joinService.setJoinView(this)

        joinService.join(getUser())
    }

    override fun onJoinSuccess() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    override fun onJoinFailure(code: Int, message: String) {
    }
}

interface JoinView {
    fun onJoinSuccess()
    fun onJoinFailure(code: Int, message: String)
}