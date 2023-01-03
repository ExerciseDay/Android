package com.example.exerciseday_android.ui.find

import com.example.exerciseday_android.databinding.ActivityFindIdBinding
import com.example.exerciseday_android.databinding.ActivityFindIdResultBinding
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.R
import com.example.exerciseday_android.*
import com.example.exerciseday_android.ui.DefaultDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindIdActivity : AppCompatActivity() {
    lateinit var binding: ActivityFindIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)

        binding.findIdNextBtn.isEnabled = false

        binding.findIdBackBtn.setOnClickListener {
            finish()
        }

        /*전화번호 입력*/
        binding.findIdPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.findIdPhoneEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val textNum = binding.findIdPhoneEt.length()

                if(textNum in 1..12){
                    binding.findIdAlertTv.visibility = View.VISIBLE
                    binding.findIdNextBtn.isEnabled = false
                    binding.findIdPhoneEt.setBackgroundResource(R.drawable.et_pink0)
                } else {
                    binding.findIdAlertTv.visibility = View.INVISIBLE
                    binding.findIdNextBtn.isEnabled = true
                    binding.findIdPhoneEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                }
                binding.findIdPhoneDeleteBtn.visibility = View.VISIBLE
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.findIdPhoneEt.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    binding.findIdPhoneDeleteBtn.visibility = View.VISIBLE
                } else {
                    binding.findIdPhoneDeleteBtn.visibility = View.INVISIBLE
                }
            }

        binding.findIdPhoneDeleteBtn.setOnClickListener {
            binding.findIdPhoneEt.setText("")
            binding.findIdNextBtn.isEnabled = false
        }

        binding.findIdNextBtn.setOnClickListener {
            findId()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)
    }

    private fun findId() {
        val dialog = DefaultDialog(this)
        val intent = Intent(this, FindIdResultActivity::class.java)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        val phone: String = binding.findIdPhoneEt.text.toString().replace("-","")

        server.findId(phone).enqueue(object : Callback<FindIdResponse> {
            override fun onFailure(call: Call<FindIdResponse>, t: Throwable) {
                Log.d("server", t.toString())
                dialog.show("일치하는 계정이 없습니다.\n다시 입력해주세요.")
            }

            override fun onResponse(call: Call<FindIdResponse>, response: Response<FindIdResponse>) {
                Log.d("server", response.body().toString())
                val isSuccess = response.body()!!.isSuccess

                if (isSuccess){
                    intent.putExtra("email", response.body()!!.result.email)
                    intent.putExtra("create", response.body()!!.result.userCreate)
                    startActivity(intent)
                } else {
                    dialog.show("일치하는 계정이 없습니다.\n다시 입력해주세요.")
                }
            }
        })
    }
}