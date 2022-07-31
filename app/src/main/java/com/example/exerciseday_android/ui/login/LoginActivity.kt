package com.example.exerciseday_android.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.LoginResponse
import com.example.exerciseday_android.PostLogin
import com.example.exerciseday_android.SignUpActivity
import com.example.exerciseday_android.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.isEnabled = false

        binding.loginBackBtn.setOnClickListener {
            finish()
        }

        binding.loginBtn.setOnClickListener {
            login()
        }

        binding.loginSignInBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginPasswordEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val msg = binding.loginPasswordEt.text.toString()
                binding.loginBtn.isEnabled = msg.isNotEmpty()

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    private fun login() {
        if (binding.loginEmailEt.text.toString().isEmpty() || binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val email : String = binding.loginEmailEt.text.toString()
        val password : String = binding.loginPasswordEt.text.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.login(PostLogin(email, password)).enqueue(object : Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("server", t.toString())
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("server", response.body().toString())
            }
        })
    }
}
