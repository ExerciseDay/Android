package com.example.exerciseday_android.ui.find

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.*
import com.example.exerciseday_android.databinding.ActivityNewPwBinding
import com.example.exerciseday_android.ui.ChangeDialog
import com.example.exerciseday_android.ui.DefaultDialog
import com.example.exerciseday_android.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class NewPwActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newPwValid = false
        var checkValid = false

        binding.newPwNextBtn.isEnabled = false

        binding.newPwBackBtn.setOnClickListener {
            finish()
        }

        binding.newPwPwEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val pwValidation = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"
                val newPw = binding.newPwPwEt.text.toString().trim()
                val isPattern = Pattern.matches(pwValidation, newPw)

                if(isPattern){
                    binding.newPwPwAlertTv.visibility = View.GONE
                    binding.newPwPwEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                    newPwValid = true
                } else {
                    binding.newPwPwAlertTv.visibility = View.VISIBLE
                    binding.newPwPwEt.setBackgroundResource(R.drawable.et_pink0)
                    newPwValid = false
                }
                binding.newPwPwDeleteBtn.visibility = View.VISIBLE
                binding.newPwNextBtn.isEnabled = newPwValid && checkValid
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.newPwCheckEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newPw = binding.newPwPwEt.text.toString()
                val check = binding.newPwCheckEt.text.toString()

                if(newPw == check){
                    binding.newPwCheckAlertTv.visibility = View.GONE
                    binding.newPwCheckEt.setBackgroundResource(R.drawable.et_gray800_to_gray950)
                    checkValid = true
                } else {
                    binding.newPwCheckAlertTv.visibility = View.VISIBLE
                    binding.newPwCheckEt.setBackgroundResource(R.drawable.et_pink0)
                    checkValid = false
                }
                binding.newPwCheckDeleteBtn.visibility = View.VISIBLE
                binding.newPwNextBtn.isEnabled = newPwValid && checkValid
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.newPwNextBtn.setOnClickListener {
            val userIdx = intent.getIntExtra("userIdx",0)
            val jwt = intent.getStringExtra("jwt")!!
            val pw = binding.newPwPwEt.text.toString()
            editPw(jwt, userIdx, pw)
        }

        binding.newPwPwDeleteBtn.setOnClickListener {
            binding.newPwPwEt.setText("")
            binding.newPwNextBtn.isEnabled = false
        }

        binding.newPwCheckDeleteBtn.setOnClickListener {
            binding.newPwCheckEt.setText("")
            binding.newPwNextBtn.isEnabled = false
        }
    }
    private fun editPw(jwt: String, userIdx: Int, pw: String) {
        Log.d("jwt", jwt)
        val dialog = DefaultDialog(this)
        val changeDialog = ChangeDialog(this)
        val intent = Intent(this, LoginActivity::class.java)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.editPw(jwt, PatchEditPw(userIdx, pw)).enqueue(object : Callback<NewPwResponse> {
            override fun onFailure(call: Call<NewPwResponse>, t: Throwable) {
                Log.d("server", t.toString())
                dialog.show("재설정에 실패했습니다.\n다시 시도해주세요.")
            }

            override fun onResponse(call: Call<NewPwResponse>, response: Response<NewPwResponse>) {
                Log.d("server", response.body().toString())
                Log.d("code", response.body()!!.code.toString())

                when(response.body()!!.code){
                    1000->{
                        changeDialog.show("비밀번호 변경이\n완료되었습니다.")
                        changeDialog.btnClickListener {
                            startActivity(intent)
                        }
                    }
                    3061->{
                        dialog.show("기존의 비밀번호와 같은 비밀번호는\n사용할 수 없습니다.")
                    }

                }
            }
        })
    }
}