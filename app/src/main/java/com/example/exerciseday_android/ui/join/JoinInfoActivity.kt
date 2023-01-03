package com.example.exerciseday_android.ui.join

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.data.remote.users.UsersService
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityJoinInfoBinding
import java.util.regex.Pattern


class JoinInfoActivity : AppCompatActivity(), EmailCheckView, View.OnClickListener {

    lateinit var binding: ActivityJoinInfoBinding

    var isEmailValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)


        // 뒤로 가기
        binding.joinInfoBackBtn.setOnClickListener(this)

        // 다음 버튼 클릭 시
        binding.joinNextBtn.setOnClickListener(this)

        // 이메일 중복 버튼 클릭 시
        binding.joinEmailCheckBtn.setOnClickListener(this)

        checkEmail()
        checkNickname()
        checkPassword()
        checkPasswordCheck()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)

    }

    private fun emailCheck() {
        val email: String = binding.joinEmailEt.text.toString()

        val joinService = UsersService()
        joinService.setEmailCheckView(this)

        joinService.emailCheck(email)
    }

    override fun onEmailCheckSuccess() {
        Log.d("EMAIL_CHECK/SUCCESS", "사용 가능한 이메일입니다.")

        isEmailValid = true

        binding.joinEmailErrorTv.text = "사용 가능한 이메일입니다."
        binding.joinEmailErrorTv.visibility = View.VISIBLE
        binding.joinEmailErrorTv.setTextColor(Color.parseColor("#000000"))
        binding.joinEmailEt.setBackgroundResource(R.drawable.join_edittext_shape)
        binding.joinEmailCheckBtn.isEnabled = false
        binding.joinEmailCheckBtn.setTextColor(Color.parseColor("#DEDEDE"))

        isValidBtn()
    }

    override fun onEmailCheckFailure(message: String) {
        Log.d("EMAIL_CHECK/FAILURE", message)

        isEmailValid = false

        binding.joinEmailErrorTv.text = message
        binding.joinEmailErrorTv.visibility = View.VISIBLE
        binding.joinEmailErrorTv.setTextColor(Color.parseColor("#ED022D"))
        binding.joinEmailEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
        binding.joinEmailCheckBtn.isEnabled = true
        binding.joinEmailCheckBtn.setTextColor(Color.parseColor("#63666A"))
    }


    private fun allCheck(): Boolean {
        // 모든 조건 충족 시
        if (binding.joinEmailEt.text.isNotEmpty() && binding.joinNicknameEt.text.isNotEmpty() && binding.joinPasswordEt.text.isNotEmpty() && binding.joinPasswordCheckEt.text.isNotEmpty() &&
            binding.joinEmailErrorTv.text == "사용 가능한 이메일입니다." && binding.joinNicknameErrorTv.visibility == View.GONE
            && binding.joinPasswordErrorTv.visibility == View.GONE && binding.joinPasswordCheckErrorTv.visibility == View.GONE
        ) {
            binding.joinNextBtn.isEnabled = true

            val email: String = binding.joinEmailEt.text.toString()
            val nickname: String = binding.joinNicknameEt.text.toString()
            val password: String = binding.joinPasswordEt.text.toString()

            sendJoinInfoList(email, nickname, password)

            return true
        } else {
            binding.joinNextBtn.isEnabled = false

            return false
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.join_info_back_btn -> finish()
            R.id.join_next_btn -> allCheck()
            R.id.join_email_check_btn -> emailCheck()
        }
    }


    private fun isValidBtn() {
        if (binding.joinEmailEt.text.isNotEmpty() && binding.joinNicknameEt.text.isNotEmpty() &&
            binding.joinPasswordEt.text.isNotEmpty() && binding.joinPasswordCheckEt.text.isNotEmpty() &&
            (binding.joinEmailErrorTv.text == "사용 가능한 이메일입니다.") && (binding.joinNicknameErrorTv.visibility == View.GONE) &&
            (binding.joinPasswordErrorTv.visibility == View.GONE) && (binding.joinPasswordCheckErrorTv.visibility == View.GONE)
        ) {
            binding.joinNextBtn.isEnabled = true
            binding.joinNextBtn.setTextColor(Color.parseColor("#ffffff"))
        } else {
            binding.joinNextBtn.isEnabled = false
            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))
        }
    }


    private fun checkEmail(): Boolean {
        binding.joinEmailEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (binding.joinEmailEt.text.isNotEmpty()) {
                    binding.joinEmailRemoveBtn.visibility = View.VISIBLE

                    binding.joinEmailRemoveBtn.setOnClickListener {
                        binding.joinEmailEt.text.clear()
                    }
                } else {
                    binding.joinEmailRemoveBtn.visibility = View.INVISIBLE
                }

                isValidBtn()

                binding.joinEmailEt.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        isEmailValid = false
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s?.isNotEmpty() == true) {
                            binding.joinEmailRemoveBtn.visibility = View.VISIBLE

                            binding.joinEmailRemoveBtn.setOnClickListener {
                                binding.joinEmailEt.text.clear()
                            }
                        } else {
                            binding.joinEmailRemoveBtn.visibility = View.INVISIBLE
                        }


                        if (binding.joinEmailEt.text.isEmpty()) {
                            binding.joinEmailErrorTv.text = "올바른 이메일을 입력해주세요."
                            binding.joinEmailErrorTv.visibility = View.VISIBLE
                            // EditText border 색 변경
                            binding.joinEmailEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
                            binding.joinEmailErrorTv.setTextColor(Color.parseColor("#ED022D"))

                            binding.joinNextBtn.isEnabled = false
                            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))

                            binding.joinEmailCheckBtn.isEnabled = true
                            binding.joinEmailCheckBtn.setTextColor(Color.parseColor("#63666A"))
                        } else if (!Pattern.matches(
                                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
                                binding.joinEmailEt.text.toString()
                            )
                        ) {
                            binding.joinEmailErrorTv.text = "이메일 형식을 확인해주세요."
                            binding.joinEmailErrorTv.visibility = View.VISIBLE
                            // EditText border 색 변경
                            binding.joinEmailEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
                            binding.joinEmailErrorTv.setTextColor(Color.parseColor("#ED022D"))

                            binding.joinNextBtn.isEnabled = false
                            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))

                            binding.joinEmailCheckBtn.isEnabled = true
                            binding.joinEmailCheckBtn.setTextColor(Color.parseColor("#63666A"))
                        } else {
                            if (!isEmailValid) {
                                binding.joinEmailErrorTv.text = "이메일 중복 확인을 해주세요."
                                binding.joinEmailErrorTv.visibility = View.VISIBLE
                                // EditText border 색 변경
                                binding.joinEmailEt.setBackgroundResource(R.drawable.join_edittext_error_shape)
                                binding.joinEmailErrorTv.setTextColor(Color.parseColor("#ED022D"))

                                binding.joinEmailCheckBtn.isEnabled = true
                                binding.joinEmailCheckBtn.setTextColor(Color.parseColor("#63666A"))
                            } else {
                                binding.joinEmailErrorTv.visibility = View.GONE
                                // EditText border 색 변경 (원래대로)
                                binding.joinEmailEt.setBackgroundResource(R.drawable.join_edittext_shape)
                                binding.joinEmailErrorTv.setTextColor(Color.parseColor("#ED022D"))

                                binding.joinEmailCheckBtn.isEnabled = false
                                binding.joinEmailCheckBtn.setTextColor(Color.parseColor("#DEDEDE"))
                            }

                            isValidBtn()
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
            } else {
                binding.joinEmailRemoveBtn.visibility = View.INVISIBLE

                isValidBtn()
            }
        }

        return binding.joinEmailErrorTv.visibility == View.GONE
    }

    private fun checkNickname(): Boolean {
        binding.joinNicknameEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (binding.joinNicknameEt.text.isNotEmpty()) {
                    binding.joinNicknameRemoveBtn.visibility = View.VISIBLE

                    binding.joinNicknameRemoveBtn.setOnClickListener {
                        binding.joinNicknameEt.text.clear()
                    }
                } else {
                    binding.joinNicknameRemoveBtn.visibility = View.INVISIBLE
                }

                isValidBtn()

                binding.joinNicknameEt.addTextChangedListener(object : TextWatcher {
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
                            binding.joinNicknameRemoveBtn.visibility = View.VISIBLE

                            binding.joinNicknameRemoveBtn.setOnClickListener {
                                binding.joinNicknameEt.text.clear()
                            }
                        } else {
                            binding.joinNicknameRemoveBtn.visibility = View.INVISIBLE
                        }


                        if (binding.joinNicknameEt.text.isEmpty()) {
                            binding.joinNicknameErrorTv.text = "닉네임을 입력해주세요."
                            binding.joinNicknameErrorTv.visibility = View.VISIBLE
                            // EditText border 색 변경
                            binding.joinNicknameEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinNextBtn.isEnabled = false
                            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinNicknameErrorTv.visibility = View.GONE
                            // EditText border 색 변경 (원래대로)
                            binding.joinNicknameEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            isValidBtn()
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
            } else {
                binding.joinNicknameRemoveBtn.visibility = View.INVISIBLE

                isValidBtn()
            }
        }

        return binding.joinNicknameErrorTv.visibility == View.GONE
    }

    private fun checkPassword(): Boolean {
        binding.joinPasswordEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (binding.joinPasswordEt.text.isNotEmpty()) {
                    binding.joinPasswordRemoveBtn.visibility = View.VISIBLE

                    binding.joinPasswordRemoveBtn.setOnClickListener {
                        binding.joinPasswordEt.text.clear()
                    }
                } else {
                    binding.joinPasswordRemoveBtn.visibility = View.INVISIBLE
                }

                isValidBtn()

                binding.joinPasswordEt.addTextChangedListener(object : TextWatcher {
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
                            binding.joinPasswordRemoveBtn.visibility = View.VISIBLE

                            binding.joinPasswordRemoveBtn.setOnClickListener {
                                binding.joinPasswordEt.text.clear()
                            }
                        } else {
                            binding.joinPasswordRemoveBtn.visibility = View.INVISIBLE
                        }


                        if (binding.joinPasswordEt.text.isEmpty() || binding.joinPasswordEt.text.length < 8 || binding.joinPasswordEt.text.length > 16) {
                            binding.joinPasswordErrorTv.text = "숫자/영문/특수문자를 포함해 8~16자로 입력해주세요."
                            binding.joinPasswordErrorTv.visibility = View.VISIBLE
                            // EditText border 색 변경
                            binding.joinPasswordEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinNextBtn.isEnabled = false
                            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))
                        } else if (!Pattern.matches(
                                "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,16}$",
                                binding.joinPasswordEt.text.toString()
                            )
                        ) {
                            binding.joinPasswordErrorTv.text = "숫자/영문/특수문자를 포함해 8~16자로 입력해주세요."
                            binding.joinPasswordErrorTv.visibility = View.VISIBLE
                            // EditText border 색 변경
                            binding.joinPasswordEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinNextBtn.isEnabled = false
                            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinPasswordErrorTv.visibility = View.GONE
                            // EditText border 색 변경 (원래대로)
                            binding.joinPasswordEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            isValidBtn()
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
            } else {
                binding.joinPasswordRemoveBtn.visibility = View.INVISIBLE

                isValidBtn()
            }
        }

        return binding.joinPasswordErrorTv.visibility == View.GONE
    }

    private fun checkPasswordCheck(): Boolean {
        binding.joinPasswordCheckEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                if (binding.joinPasswordCheckEt.text.isNotEmpty()) {
                    binding.joinPasswordCheckRemoveBtn.visibility = View.VISIBLE

                    binding.joinPasswordCheckRemoveBtn.setOnClickListener {
                        binding.joinPasswordCheckEt.text.clear()
                    }
                } else {
                    binding.joinPasswordCheckRemoveBtn.visibility = View.INVISIBLE
                }

                isValidBtn()

                binding.joinPasswordCheckEt.addTextChangedListener(object : TextWatcher {
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
                            binding.joinPasswordCheckRemoveBtn.visibility = View.VISIBLE

                            binding.joinPasswordCheckRemoveBtn.setOnClickListener {
                                binding.joinPasswordCheckEt.text.clear()
                            }
                        } else {
                            binding.joinPasswordCheckRemoveBtn.visibility = View.INVISIBLE
                        }


                        if (binding.joinPasswordEt.text.toString() != binding.joinPasswordCheckEt.text.toString()) {
                            binding.joinPasswordCheckErrorTv.text = "입력하신 비밀번호와 일치하지 않아요."
                            binding.joinPasswordCheckErrorTv.visibility = View.VISIBLE
                            // EditText border 색 변경
                            binding.joinPasswordCheckEt.setBackgroundResource(R.drawable.join_edittext_error_shape)

                            binding.joinNextBtn.isEnabled = false
                            binding.joinNextBtn.setTextColor(Color.parseColor("#909397"))
                        } else {
                            binding.joinPasswordCheckErrorTv.visibility = View.GONE
                            // EditText border 색 변경 (원래대로)
                            binding.joinPasswordCheckEt.setBackgroundResource(R.drawable.join_edittext_shape)

                            isValidBtn()
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
            } else {
                binding.joinPasswordCheckRemoveBtn.visibility = View.INVISIBLE

                isValidBtn()
            }
        }

        return binding.joinPasswordCheckErrorTv.visibility == View.GONE
    }


    private fun sendJoinInfoList(email: String, nickname: String, password: String) {
        val joinInfoList = ArrayList<String>()

        joinInfoList.add(email)
        joinInfoList.add(nickname)
        joinInfoList.add(password)

        val intent = Intent(this, JoinPhoneActivity::class.java)
        intent.putStringArrayListExtra("join", joinInfoList)

        startActivity(intent)
    }
}

interface EmailCheckView {
    fun onEmailCheckSuccess()
    fun onEmailCheckFailure(message: String)
}