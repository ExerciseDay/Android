package com.example.exerciseday_android

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivitySignUpBinding
    var emailPattern: Pattern = Patterns.EMAIL_ADDRESS
//    val emailValidation = "^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpNextBtn.setOnClickListener {
            signUp()
        }

        clearNickname()  // 닉네임 길이 > 0일 경우 x버튼 활성화

    }

    private fun getUser(): User {
        val email: String = binding.signUpEmailEdt.text.toString()
        val nickname: String = binding.signUpNicknameEdt.text.toString()
        val password: String = binding.signUpPasswordEdt.text.toString()
        val phone: String = "01054545454"  // 수정 예정

        return User(email, password, nickname, phone)
    }

    private fun signUp() {

        // 이메일 체크, 닉네임 체크, 비밀번호 체크, 비밀번호 확인 체크
        if (checkEmail() && checkNickname() && checkPassword() && checkPasswordCheck()) {
            val authService = AuthService()
            authService.setSignUpView(this)

            authService.signUp(getUser())
        }

        binding.signUpEmailCheckBtn.setOnClickListener {
            // 이메일 중복 여부 검사 (추가 예정)
        }


    }

    override fun onSignUpSuccess() {
        finish()
    }

    override fun onSignUpFailure(code: Int, message: String) {
        when (code) {
            2015, 2016, 3013 -> {
                binding.signUpEmailErrorTv.visibility = View.VISIBLE
                binding.signUpEmailErrorTv.text = message
            }
            2018, 2019 -> {
                binding.signUpNicknameErrorTv.visibility = View.VISIBLE
                binding.signUpNicknameErrorTv.text = message
            }
            2022 -> {
                binding.signUpPasswordErrorTv.visibility = View.VISIBLE
            }
            2023, 3015 -> {
                // 전화번호 오류 관련 (추가 예정)
            }
            else -> {
                binding.signUpEmailErrorTv.visibility = View.VISIBLE
                binding.signUpEmailErrorTv.text = message
            }
        }
    }

    private fun checkEmail(): Boolean {
        Log.d("email", binding.signUpEmailEdt.text.toString())
        if (binding.signUpEmailEdt.text.toString().isEmpty()) {
            binding.signUpEmailErrorTv.visibility = View.VISIBLE
            binding.signUpEmailErrorTv.text = "올바른 이메일을 입력해주세요."

            return false
        } else if (!emailPattern.matcher(binding.signUpEmailEdt.text.toString()).matches()) {
            binding.signUpEmailErrorTv.visibility = View.VISIBLE
            binding.signUpEmailErrorTv.text = "이메일 형식을 확인해주세요."

            return false
        } else {
            binding.signUpEmailErrorTv.visibility = View.GONE

            return true
        }
    }

    private fun clearNickname() {
        binding.signUpNicknameEdt.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                binding.signUpNicknameEdt.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        Log.d("nickname", s.toString())

                        if (s?.isNotEmpty() == true) {
                            binding.signUpNicknameRemoveBtn.visibility = View.VISIBLE

                            binding.signUpNicknameRemoveBtn.setOnClickListener {
                                binding.signUpNicknameEdt.text.clear()
                            }
                        } else {
                            binding.signUpNicknameRemoveBtn.visibility = View.INVISIBLE
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
            }
            else {
                binding.signUpNicknameRemoveBtn.visibility = View.INVISIBLE
            }
        }
    }

    private fun checkNickname(): Boolean {
        if (binding.signUpNicknameEdt.text.toString().isEmpty()) {
            binding.signUpNicknameErrorTv.visibility = View.VISIBLE
            binding.signUpNicknameErrorTv.text = "닉네임을 입력해주세요."

            return false
        } else {
            binding.signUpNicknameErrorTv.visibility = View.GONE

            return true
        }
    }

    private fun checkPassword(): Boolean {
        if (binding.signUpPasswordEdt.text.toString()
                .isEmpty() || binding.signUpPasswordEdt.text.toString().length < 8 || binding.signUpPasswordEdt.text.toString().length > 16
        ) {
            binding.signUpPasswordErrorTv.visibility = View.VISIBLE
            binding.signUpPasswordErrorTv.text = "숫자/영문/특수문자를 포함해 8~16자로 입력해주세요."

            return false
        } else {
            binding.signUpPasswordErrorTv.visibility = View.GONE

            return true
        }

    }

    private fun checkPasswordCheck(): Boolean {
        if (binding.signUpPasswordEdt.text.toString() != binding.signUpPasswordCheckEdt.text.toString()) {
            binding.signUpPasswordCheckErrorTv.visibility = View.VISIBLE
            binding.signUpPasswordCheckErrorTv.text = "입력하신 비밀번호와 일치하지 않아요."

            return false
        } else {
            binding.signUpPasswordCheckErrorTv.visibility = View.GONE

            return true
        }
    }
}