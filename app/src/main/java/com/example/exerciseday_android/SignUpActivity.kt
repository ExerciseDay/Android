package com.example.exerciseday_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.exerciseday_android.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            signUp()
        }
    }

    private fun getUser(): User {
        val email: String =
            binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
        val pwd: String = binding.signUpPasswordEt.text.toString()
        val nickname: String = binding.signUpNicknameEt.text.toString()
        var path: String = ""

        // 가입경로 체크박스 선택값에 따른 path 값 설정
        if (binding.signUpBlogCb.isChecked) {
            path = binding.signUpBlogCb.text.toString()
        }
        else if (binding.signUpSnsCb.isChecked) {
            path = binding.signUpSnsCb.text.toString()
        }
        else if (binding.signUpAdCb.isChecked) {
            path = binding.signUpAdCb.text.toString()
        }
        else if (binding.signUpSearchCb.isChecked) {
            path = binding.signUpSearchCb.text.toString()
        }
        else if (binding.signUpEtcCb.isChecked) {
            path = binding.signUpEtcCb.text.toString()
        }

        return User(email, pwd, nickname, path)
    }

    private fun signUp() {
        if (binding.signUpIdEt.text.toString()
                .isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpNicknameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }


        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())
    }

    override fun onSignUpSuccess() {
        finish()
    }

    override fun onSignUpFailure(message: String) {
//        binding.signUpEmailErrorTv.visibility = View.VISIBLE
//        binding.signUpEmailErrorTv.text = message
    }
}