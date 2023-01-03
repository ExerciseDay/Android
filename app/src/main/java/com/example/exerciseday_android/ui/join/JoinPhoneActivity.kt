package com.example.exerciseday_android.ui.join

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.data.remote.users.UsersService
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityJoinPhoneBinding
import com.example.exerciseday_android.ui.ChangeDialog
import java.util.*
import java.util.regex.Pattern
import kotlin.concurrent.timer


class JoinPhoneActivity : AppCompatActivity(), VerificationCodeView, View.OnClickListener {

    lateinit var binding: ActivityJoinPhoneBinding

    private var time = 60 * 3
    private var timerTask: Timer? = null

    private var isVerified = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)

        // 뒤로 가기
        binding.joinPhoneBackBtn.setOnClickListener(this)

        // 인증번호 받기 버튼 클릭 시
        binding.joinVerificationCodeBtn.setOnClickListener {
            isVerified = true

            binding.joinPhoneEt.isClickable = false
            binding.joinPhoneEt.isFocusable = false

            // 다이얼로그 창
            showVerificationCodeDialog()

            checkVerificationCode()
        }

        // 인증번호 재발송 버튼 클릭 시
        binding.joinVerificationCodeAgainBtn.setOnClickListener(this)

        // 회원가입 버튼 클릭 시
        binding.joinJoinBtn.setOnClickListener {
            timerTask?.cancel()

            val phone: String = binding.joinPhoneEt.text.toString().replace("-", "")

            sendJoinPhone(phone)
        }

        checkPhone()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.join_phone_back_btn -> finish()
            R.id.join_verification_code_again_btn -> {
                verificationCode(binding.joinPhoneEt.text.toString().replace("-", ""))
                reset()
            }
        }
    }

    private fun checkPhone(): Boolean {
        binding.joinPhoneEt.setOnFocusChangeListener { _, hasFocus ->
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

                            binding.joinVerificationCodeBtn.isEnabled = false
                            binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#909397"))
                        } else if (!Pattern.matches(
                                "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
                                binding.joinPhoneEt.text
                            )
                        ) {
                            binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                            binding.joinPhoneErrorTv.visibility = View.VISIBLE
                            binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinVerificationCodeBtn.isEnabled = false
                            binding.joinVerificationCodeBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinPhoneErrorTv.visibility = View.GONE
                            // EditText border 색 변경 (원래대로)
                            binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            // 인증번호 받기 버튼 색 변경
                            binding.joinVerificationCodeBtn.isEnabled = true
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
        binding.joinVerificationCodeEt.setOnFocusChangeListener { _, hasFocus ->
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


                        if (binding.joinVerificationCodeEt.text.length < 6) {
                            binding.joinVerificationCodeErrorTv.text = "인증번호를 입력해주세요."
                            binding.joinVerificationCodeErrorTv.visibility = View.VISIBLE
                            binding.joinVerificationCodeEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinJoinBtn.isEnabled = false
                            binding.joinJoinBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinVerificationCodeErrorTv.visibility = View.INVISIBLE
                            // EditText border 색 변경 (원래대로)
                            binding.joinVerificationCodeEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            // 가입하기 버튼 색 변경
                            binding.joinJoinBtn.isEnabled = true
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
        val dialog = ChangeDialog(this)
        dialog.show("인증번호가 발송되었습니다. 3분 안에 \n인증번호를 입력해주세요.")

        dialog.btnClickListener {
            // 인증번호 받기 버튼 숨김, 보여야 할 뷰 표시
            binding.joinVerificationCodeBtn.visibility = View.GONE
            binding.joinVerificationCodeEt.visibility = View.VISIBLE
            binding.joinVerificationCodeAgainBtn.visibility = View.VISIBLE

            binding.joinVerificationCodeEt.requestFocus()

            // 본인인증 문자 전송
            val phone: String = binding.joinPhoneEt.text.toString().replace("-", "")
            verificationCode(phone)

            // 인증번호 입력 Timer 시작
            start()
        }
    }


    // 전화번호 인증번호 시간 만료 Dialog
    private fun showTimeExpiredDialog() {
        val dialog = ChangeDialog(this)
        dialog.show(getString(R.string.join_phone_verification_code_time_out))

        dialog.btnClickListener {
            // 본인인증 문자 전송
            val phone: String = binding.joinPhoneEt.text.toString().replace("-", "")
            verificationCode(phone)

            // 인증번호 입력 Timer 재시작
            reset()
        }
    }

    private fun verificationCode(phone: String) {
        val joinService = UsersService()
        joinService.setVerificationCodeView(this)

        joinService.verificationCode(phone)
    }

    override fun onVerificationCodeSuccess(result: String) {
        binding.joinVerificationCodeEt.setText(result)
        binding.joinVerificationCodeEt.setSelection(binding.joinVerificationCodeEt.length())
    }

    override fun onVerificationCodeFailure(message: String) {
        TODO("Not yet implemented")
    }


    private fun start() {
        timerTask = timer(period = 1000) {
            try {
                val min: Int = time / 60
                val sec: Int = time % 60

                time--

                runOnUiThread {
                    if (min == 0 && sec == 0) {
                        timerTask?.cancel()
                        showTimeExpiredDialog()
                    }
                }

                runOnUiThread {
                    binding.joinVerificationCodeTimerTv.text =
                        String.format("%02d:%02d", min, sec)
                    binding.joinVerificationCodeTimerTv.visibility = View.VISIBLE
                }
            } catch (e: InterruptedException) {
                Log.d("Timer", "쓰레드가 죽었습니다. ${e.message}")
            }
        }
    }

    private fun reset() {
        timerTask?.cancel()

        time = 60 * 3

        start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        timerTask?.cancel()
    }

    private fun sendJoinPhone(phone: String) {
        val joinInfoList = intent.extras!!.getStringArrayList("join")

        val joinPhone: String = phone

        joinInfoList!!.add(joinPhone)

        val intent = Intent(this, JoinGenderActivity::class.java)
        intent.putStringArrayListExtra("join", joinInfoList)

        startActivity(intent)
    }
}

interface VerificationCodeView {
    fun onVerificationCodeSuccess(result: String)
    fun onVerificationCodeFailure(message: String)
}