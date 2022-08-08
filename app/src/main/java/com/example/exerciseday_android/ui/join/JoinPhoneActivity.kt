package com.example.exerciseday_android.ui.join

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.JoinService
import com.example.exerciseday_android.JoinView
import com.example.exerciseday_android.R
import com.example.exerciseday_android.User
import com.example.exerciseday_android.databinding.ActivityJoinPhoneBinding
import kotlinx.android.synthetic.main.dialog_custom_join_verification_code.view.*
import java.util.regex.Pattern

class JoinPhoneActivity : AppCompatActivity(), JoinView, View.OnClickListener {

    lateinit var binding: ActivityJoinPhoneBinding

    private var isVerified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO
        // 시간 만료 다이얼로그 창


        // 인증번호 받기 버튼 클릭 시
        binding.joinVerificationCodeBtn.setOnClickListener {
            isVerified = true
            // 전화번호 체크
            if (binding.joinPhoneEt.text.isEmpty()) {
                binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                binding.joinPhoneErrorTv.visibility = View.VISIBLE
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
            } else if (!Pattern.matches(
                    "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
                    binding.joinPhoneEt.text
                )
            ) {
                binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                binding.joinPhoneErrorTv.visibility = View.VISIBLE
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

            } else {
                binding.joinPhoneErrorTv.visibility = View.GONE
                // EditText border 색 변경 (원래대로)
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_shape)

                // 다이얼로그 창
                showVerificationCodeDialog()
            }

            checkVerificationCode()
        }

        // 회원가입 버튼 클릭 시
        binding.joinJoinBtn.setOnClickListener {
            // 전화번호 체크
            if (binding.joinPhoneEt.text.isEmpty()) {
                binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                binding.joinPhoneErrorTv.visibility = View.VISIBLE
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                binding.joinVerificationCodeBtn.setBackgroundResource(R.drawable.join_invalid_btn_shape)
                binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#909397"))
            } else if (!Pattern.matches(
                    "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
                    binding.joinPhoneEt.text
                )
            ) {
                binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                binding.joinPhoneErrorTv.visibility = View.VISIBLE
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                binding.joinVerificationCodeBtn.setBackgroundResource(R.drawable.join_invalid_btn_shape)
                binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#909397"))
            } else {
                binding.joinPhoneErrorTv.visibility = View.GONE
                // EditText border 색 변경 (원래대로)
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_shape)
            }

            // 모든 조건 충족 시
            if (binding.joinPhoneEt.text.isNotEmpty() && binding.joinVerificationCodeEt.text.isNotEmpty() &&
                (binding.joinPhoneErrorTv.visibility == View.GONE) && (binding.joinVerificationCodeErrorTv.visibility == View.GONE) &&
                isVerified
            ) {
                join()
            }
        }

        checkPhone()
    }

    private fun getUser(): User {
        // JoinInfoActivity 에서 가입 입력정보 전달 받기
        val joinInfoList = intent.extras!!.getStringArrayList("joinInfo")

        val email: String = joinInfoList!![0]
        val nickname: String = joinInfoList!![1]
        val password: String = joinInfoList!![2]

        val phone: String = binding.joinPhoneEt.text.toString().replace("-", "")

        Log.d("join_getUser", User(email, password, nickname, phone).toString())

        return User(email, password, nickname, phone)
    }

    private fun join() {
        val joinService = JoinService()
        joinService.setJoinView(this)

        joinService.join(getUser())
    }

    override fun onJoinSuccess() {
        val intent = Intent(this, PurposeSettingActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onJoinFailure(code: Int, message: String) {
        binding.joinVerificationCodeErrorTv.visibility = View.VISIBLE
        binding.joinVerificationCodeErrorTv.text = message
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.join_phone_back_btn -> finish()
        }
    }

    private fun checkPhone(): Boolean {
        binding.joinPhoneEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.joinPhoneEt.text.isNotEmpty()) {
                    binding.joinPhoneRemoveBtn.visibility = View.VISIBLE

                    binding.joinPhoneRemoveBtn.setOnClickListener {
                        binding.joinPhoneEt.text.clear()
                    }
                } else {
                    binding.joinPhoneRemoveBtn.visibility = View.INVISIBLE
                }

                binding.joinPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
                binding.joinPhoneEt.addTextChangedListener(object :
                    TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s?.isNotEmpty() == true) {
                            binding.joinPhoneRemoveBtn.visibility = View.VISIBLE

                            binding.joinPhoneRemoveBtn.setOnClickListener {
                                binding.joinPhoneEt.text.clear()
                            }
                        } else {
                            binding.joinPhoneRemoveBtn.visibility = View.INVISIBLE
                        }


                        if (binding.joinPhoneEt.text.isEmpty()) {
                            binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                            binding.joinPhoneErrorTv.visibility = View.VISIBLE
                            binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinVerificationCodeBtn.setBackgroundResource(R.drawable.join_invalid_btn_shape)
                            binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#909397"))
                        } else if (!Pattern.matches(
                                "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
                                binding.joinPhoneEt.text
                            )
                        ) {
                            binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                            binding.joinPhoneErrorTv.visibility = View.VISIBLE
                            binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinVerificationCodeBtn.setBackgroundResource(R.drawable.join_invalid_btn_shape)
                            binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinPhoneErrorTv.visibility = View.GONE
                            // EditText border 색 변경 (원래대로)
                            binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            // 인증번호 받기 버튼 색 변경
                            binding.joinVerificationCodeBtn.setBackgroundResource(R.drawable.join_valid_btn_shape)
                            binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#ffffff"))
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })

            } else {
                binding.joinPhoneRemoveBtn.visibility = View.INVISIBLE
            }
        }
        return binding.joinPhoneErrorTv.visibility == View.GONE
    }

    private fun checkVerificationCode(): Boolean {
        binding.joinVerificationCodeEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.joinVerificationCodeEt.text.isNotEmpty()) {
                    binding.joinVerificationCodeRemoveBtn.visibility = View.VISIBLE

                    binding.joinVerificationCodeRemoveBtn.setOnClickListener {
                        binding.joinVerificationCodeEt.text.clear()
                    }
                } else {
                    binding.joinVerificationCodeRemoveBtn.visibility = View.INVISIBLE
                }

                binding.joinVerificationCodeEt.addTextChangedListener(object :
                    TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s?.isNotEmpty() == true) {
                            binding.joinVerificationCodeRemoveBtn.visibility = View.VISIBLE

                            binding.joinVerificationCodeRemoveBtn.setOnClickListener {
                                binding.joinVerificationCodeEt.text.clear()
                            }
                        } else {
                            binding.joinVerificationCodeRemoveBtn.visibility = View.INVISIBLE
                        }


                        if (binding.joinVerificationCodeEt.text.length < 4) {
                            binding.joinVerificationCodeErrorTv.text = "인증번호를 입력해주세요."
                            binding.joinVerificationCodeErrorTv.visibility = View.VISIBLE
                            binding.joinVerificationCodeEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinJoinBtn.setBackgroundResource(R.drawable.join_invalid_btn_shape)
                            binding.joinJoinBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinVerificationCodeErrorTv.visibility = View.GONE
                            // EditText border 색 변경 (원래대로)
                            binding.joinVerificationCodeEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            // 가입하기 버튼 색 변경
                            binding.joinJoinBtn.setBackgroundResource(R.drawable.join_valid_btn_shape)
                            binding.joinJoinBtn.setTextColor(Color.parseColor("#ffffff"))
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })

            } else {
                binding.joinVerificationCodeRemoveBtn.visibility = View.INVISIBLE
            }
        }
        return binding.joinVerificationCodeErrorTv.visibility == View.GONE
    }


    // 전화번호 인증번호 받기 Dialog
    private fun showVerificationCodeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_join_verification_code, null)
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).apply {
            setView(dialogView)
        }
        val dialog = builder.create()
        dialog.show()

        dialogView.join_verification_code_dialog_ok_btn.setOnClickListener {
            // 인증번호 받기 버튼 숨김, 보여야 할 뷰 표시
            binding.joinVerificationCodeBtn.visibility = View.GONE
            binding.joinVerificationCodeEt.visibility = View.VISIBLE
            binding.joinVerificationCodeAgainBtn.visibility = View.VISIBLE

            binding.joinVerificationCodeEt.requestFocus()
            dialog.dismiss()
        }
    }
}