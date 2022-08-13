package com.example.exerciseday_android.ui.find

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityFindPwBinding
import com.example.exerciseday_android.ui.DefaultDialog
import java.util.regex.Pattern

class FindPwActivity : AppCompatActivity() {
    lateinit var binding: ActivityFindPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findPwBackBtn.setOnClickListener {
            finish()
        }
        
        binding.findPwEmailEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                val email = binding.findPwEmailEt.text.toString().trim()
                val phone = binding.findPwPhoneEt.text.toString()
                val isPattern = Pattern.matches(emailValidation, email)

                if(isPattern){
                    binding.findPwEmailAlertTv.visibility = View.GONE
                    binding.findPwEmailEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                } else {
                    binding.findPwEmailAlertTv.visibility = View.VISIBLE
                    binding.findPwEmailEt.setBackgroundResource(R.drawable.et_pink0)
                }
                binding.findPwEmailDeleteBtn.visibility = View.VISIBLE

                if(email.isNotEmpty() && phone.isNotEmpty()){
                    binding.findPwSendAuthBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.findPwPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.findPwPhoneEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val email = binding.findPwEmailEt.text.toString()
                val phone = binding.findPwPhoneEt.text.toString()
                val textNum = binding.findPwPhoneEt.length()

                if(textNum in 1..12){
                    binding.findPwPhoneAlertTv.visibility = View.VISIBLE
                    binding.findPwPhoneEt.setBackgroundResource(R.drawable.et_pink0)
                } else {
                    binding.findPwPhoneAlertTv.visibility = View.GONE
                    binding.findPwPhoneEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                }
                binding.findPwPhoneDeleteBtn.visibility = View.VISIBLE

                if(email.isNotEmpty() && phone.isNotEmpty()){
                    binding.findPwSendAuthBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.findPwEmailEt.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    binding.findPwEmailDeleteBtn.visibility = View.VISIBLE
                } else {
                    binding.findPwEmailDeleteBtn.visibility = View.INVISIBLE
                }
            }

        binding.findPwPhoneEt.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    binding.findPwPhoneDeleteBtn.visibility = View.VISIBLE
                } else {
                    binding.findPwPhoneDeleteBtn.visibility = View.INVISIBLE
                }
            }

        binding.findPwAuthEt.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    binding.findPwAuthDeleteBtn.visibility = View.VISIBLE
                } else {
                    binding.findPwAuthDeleteBtn.visibility = View.INVISIBLE
                }
            }

        binding.findPwEmailDeleteBtn.setOnClickListener {
            binding.findPwEmailEt.setText("")
        }

        binding.findPwPhoneDeleteBtn.setOnClickListener {
            binding.findPwPhoneEt.setText("")
        }

        binding.findPwAuthDeleteBtn.setOnClickListener {
            binding.findPwPhoneEt.setText("")
        }

        binding.findPwSendAuthBtn.setOnClickListener {
            sendAuthNumber()
        }

        binding.findPwNextBtn.setOnClickListener {
            val auth = binding.findPwAuthEt.text.toString()
            if(auth == "1234"){
                val intent = Intent(this, NewPwActivity::class.java)
                startActivity(intent)
            } else {
                val dialog = DefaultDialog(this)
                dialog.show("잘못된 인증번호입니다.\n다시 입력해주세요.")
            }
        }
    }

    private fun sendAuthNumber() {
        val dialog = DefaultDialog(this)
        dialog.show("인증번호가 발송되었습니다. 3분 안에\n인증번호를 입력해주세요.")
        dialog.btnClickListener {
            binding.findPwSendAuthBtn.visibility = View.GONE
            binding.findPwAuthL.visibility = View.VISIBLE
            binding.findPwNextBtn.visibility = View.VISIBLE
            binding.findPwAuthEt.requestFocus()
            binding.findPwEmailEt.isEnabled = false
            binding.findPwPhoneEt.isEnabled = false

            binding.findPwAuthEt.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val auth = binding.findPwAuthEt.text.toString()

                    if(auth.isNotEmpty()){
                        binding.findPwNextBtn.isEnabled = true
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
    }
}