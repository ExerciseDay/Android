package com.example.exerciseday_android.ui.join

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

    var focus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO
        // 만료 다이얼로그 창
        // 가입하기 가능 시 버튼 색 변경


        // 인증번호 받기 버튼 클릭 시
        binding.joinVerificationCodeBtn.setOnClickListener {
            // 전화번호인지 검사
            if (binding.joinPhoneEt.text.isEmpty()) {
                binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                binding.joinPhoneErrorTv.visibility = View.VISIBLE
                // EditText border 색 변경
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
            } else if (!Pattern.matches(
                    "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
                    binding.joinPhoneEt.text
                )
            ) {
                binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                binding.joinPhoneErrorTv.visibility = View.VISIBLE
                // EditText border 색 변경
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

            } else {
                binding.joinPhoneErrorTv.visibility = View.GONE
                // EditText border 색 변경 (원래대로)
                binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_shape)

                // 다이얼로그 창
                showVerificationCodeDialog()

                // 버튼 안 보이게. 보여야할 버튼 표시
            }

//
//            if (binding.joinPhoneErrorTv.visibility == View.GONE) {
//            }
        }

        // 회원가입 버튼 클릭 시
        binding.joinJoinBtn.setOnClickListener {
            join()
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
        finish()
    }

    override fun onJoinFailure(code: Int, message: String) {
//        binding.signUpEmailErrorTv.visibility = View.VISIBLE
//        binding.signUpEmailErrorTv.text = message

//        when (code) {
////            2015, 2016 -> {
////                binding.joinEmailErrorTv.visibility = View.VISIBLE
////                binding.joinEmailErrorTv.text = message
////            }
//            }
//            else -> {
//                binding.joinEmailErrorTv.visibility = View.VISIBLE
//                binding.joinEmailErrorTv.text = message
//            }
//        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun checkPhone(): Boolean {
        binding.joinPhoneEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                focus = true  // 한 번이라도 포커스를 가졌으면 true

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
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })

            } else {
                if (focus) {
                    if (binding.joinPhoneEt.text.isEmpty()) {
                        binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                        binding.joinPhoneErrorTv.visibility = View.VISIBLE
                        // EditText border 색 변경
                        binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
                    } else if (!Pattern.matches(
                            "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
                            binding.joinPhoneEt.text
                        )
                    ) {
                        binding.joinPhoneErrorTv.text = "올바른 전화번호를 입력해주세요."
                        binding.joinPhoneErrorTv.visibility = View.VISIBLE
                        // EditText border 색 변경
                        binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
                    } else {
                        binding.joinPhoneErrorTv.visibility = View.GONE
                        // EditText border 색 변경 (원래대로)
                        binding.joinPhoneEt.setBackgroundResource(R.drawable.join_edittext_shape)
                    }
                }

                binding.joinPhoneRemoveBtn.visibility = View.INVISIBLE
            }
        }

        return binding.joinPhoneErrorTv.visibility == View.GONE
    }

    // 전화번호 인증번호 받기 Dialog
    private fun showVerificationCodeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_join_verification_code, null)
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).apply {
            setView(dialogView)
        }
        val dialog = builder.create()
        dialog.show()

//        dialog.setButton()

        dialogView.join_verification_code_dialog_ok_btn.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun loadJoinInfo(): ArrayList<String> {
        var pref = applicationContext.getSharedPreferences("joinInfo", 0)
        val set: Set<String> = setOf("", "", "")

        var joinInfoSet = pref?.getStringSet("joinInfo", set)

        // 왜 순서 바꿔서 들어가는가..........
        Log.d("loadJoinInfo", joinInfoSet.toString())

        return ArrayList(joinInfoSet)
    }
}