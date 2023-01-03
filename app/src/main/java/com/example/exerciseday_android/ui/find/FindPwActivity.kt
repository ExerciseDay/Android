package com.example.exerciseday_android.ui.find

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.*
import com.example.exerciseday_android.databinding.ActivityFindPwBinding
import com.example.exerciseday_android.ui.ChangeDialog
import com.example.exerciseday_android.ui.DefaultDialog
import kotlinx.android.synthetic.main.activity_find_pw.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class FindPwActivity : AppCompatActivity() {
    lateinit var binding: ActivityFindPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)

        var emailValid = false
        var phoneValid = false

        /*다음 intent 로 전달*/
        var jwt: String = ""
        var userIdx: Int = 0

        binding.findPwBackBtn.setOnClickListener {
            finish()
        }
        
        binding.findPwEmailEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                val email = binding.findPwEmailEt.text.toString().trim()
                val isPattern = Pattern.matches(emailValidation, email)

                if(isPattern){
                    binding.findPwEmailAlertTv.visibility = View.GONE
                    binding.findPwEmailEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                    emailValid = true
                } else {
                    binding.findPwEmailAlertTv.visibility = View.VISIBLE
                    binding.findPwEmailEt.setBackgroundResource(R.drawable.et_pink0)
                    emailValid = false
                }
                binding.findPwEmailDeleteBtn.visibility = View.VISIBLE

                if(emailValid && phoneValid){
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
                    phoneValid = false
                } else {
                    binding.findPwPhoneAlertTv.visibility = View.GONE
                    binding.findPwPhoneEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                    if (textNum == 13) {
                        phoneValid = true
                    }
                }
                binding.findPwPhoneDeleteBtn.visibility = View.VISIBLE

                if(emailValid && phoneValid){
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
            binding.findPwSendAuthBtn.isEnabled = false
        }

        binding.findPwPhoneDeleteBtn.setOnClickListener {
            binding.findPwPhoneEt.setText("")
            binding.findPwSendAuthBtn.isEnabled = false
        }

        binding.findPwAuthDeleteBtn.setOnClickListener {
            binding.findPwAuthEt.setText("")
            binding.findPwNextBtn.isEnabled = false
        }

        binding.findPwSendAuthBtn.setOnClickListener {
            val dialog = DefaultDialog(this)

            val retrofit = Retrofit.Builder()
                .baseUrl("http://3.39.184.186:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val server: APIS = retrofit.create(APIS::class.java)

            val email = binding.findPwEmailEt.text.toString()
            val phone = binding.findPwPhoneEt.text.toString().replace("-","")

            server.findPw(PostFindPw(email, phone)).enqueue(object : Callback<FindPwResponse>{
                override fun onFailure(call: Call<FindPwResponse>, t: Throwable) {
                    Log.d("server", t.toString())
                }

                override fun onResponse(call: Call<FindPwResponse>, response: Response<FindPwResponse>) {
                    Log.d("server", response.body().toString())
                    val isSuccess = response.body()!!.isSuccess

                    if (isSuccess){
                        jwt = response.body()!!.result.jwt
                        userIdx = response.body()!!.result.userIdx
                        sendAuthNumber()
                    } else {
                        dialog.show("일치하는 계정이 없습니다.\n다시 입력해주세요.")
                    }
                }
            })
        }

        binding.findPwNextBtn.setOnClickListener {
            val auth = binding.findPwAuthEt.text.toString()
            if(auth == "1234" && userIdx != 0 && jwt != ""){
                val intent = Intent(this, NewPwActivity::class.java)
                intent.putExtra("userIdx", userIdx)
                intent.putExtra("jwt", jwt)
                startActivity(intent)
            } else {
                val dialog = DefaultDialog(this)
                dialog.show("잘못된 인증번호입니다.\n다시 입력해주세요.")
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)
    }

    private fun sendAuthNumber() {
        val dialog = ChangeDialog(this)
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