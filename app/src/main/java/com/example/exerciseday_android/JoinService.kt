package com.example.exerciseday_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinService {
    private lateinit var joinView: JoinView
    private lateinit var emailCheckView: EmailCheckView

    fun setJoinView(joinView: JoinView) {
        this.joinView = joinView
    }

    fun setEmailCheckView(emailCheckView: EmailCheckView) {
        this.emailCheckView = emailCheckView
    }

    // 회원가입
    fun join(user: User) {
        val joinService = getRetrofit().create(AuthRetrofitInterface::class.java)
        joinService.join(user).enqueue(object : Callback<JoinResponse> {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                Log.d("JOIN/SUCCESS", response.toString())
                val resp: JoinResponse = response.body()!!

                Log.d("JOIN/CODE", resp.code.toString())

                // 서버 response 중 code 값에 따른 결과
                when (resp.code) {
                    1000 -> joinView.onJoinSuccess()
                    else -> joinView.onJoinFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                Log.d("JOIN/FAILURE", t.message.toString())
            }
        })
        Log.d("JOIN", "HELLO")
    }


    // 이메일 중복 확인
    fun emailCheck(email: String) {
        val joinService = getRetrofit().create(AuthRetrofitInterface::class.java)
        joinService.emailCheck(email).enqueue(object : Callback<EmailCheckResponse> {
            override fun onResponse(
                call: Call<EmailCheckResponse>,
                response: Response<EmailCheckResponse>
            ) {
                Log.d("EMAIL_CHECK/SUCCESS", response.toString())

                // https://week-year.tistory.com/181 참고
//                Log.d("MAINACTIVITY", response.body().toString())
//                val resp: EmailCheckResponse = response.body()!!

                if (response.isSuccessful) {
                    when (response.body()?.result) {

                        false -> emailCheckView.onEmailCheckSuccess()  // 중복 X
                        true -> emailCheckView.onEmailCheckFailure(response.message())  // 중복 O
                        else -> {}
                    }
                } else {
                    when (response.code()) {
                        404 -> Log.d("EMAIL_CHECK/FAILURE", response.code().toString())
                    }
                }

            }

            override fun onFailure(call: Call<EmailCheckResponse>, t: Throwable) {
                Log.d("EMAIL_CHECK/FAILURE", t.message.toString())
            }
        })
    }

}