package com.example.exerciseday_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var signUpView: SignUpView

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun signUp(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.signUp(user).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("SIGNUP/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!

                // 서버 response 중 code값에 따른 결과
                when (resp.code) {
                    1000 -> signUpView.onSignUpSuccess()
                    else -> signUpView.onSignUpFailure(resp.message)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP/FAILURE", t.message.toString())
            }
        })
        Log.d("SIGNUP", "HELLO")
    }
}