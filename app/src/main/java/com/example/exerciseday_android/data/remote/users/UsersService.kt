package com.example.exerciseday_android.data.remote.users

import android.util.Log
import com.example.exerciseday_android.ui.join.EmailCheckView
import com.example.exerciseday_android.ui.join.JoinView
import com.example.exerciseday_android.ui.join.VerificationCodeView
import com.example.exerciseday_android.data.model.User
import com.example.exerciseday_android.getRetrofit
import com.example.exerciseday_android.ui.expert.PutExpertCourseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersService {
    private lateinit var joinView: JoinView
    private lateinit var emailCheckView: EmailCheckView
    private lateinit var verificationCodeView: VerificationCodeView
    private lateinit var putExpertCourseView: PutExpertCourseView


    fun setJoinView(joinView: JoinView) {
        this.joinView = joinView
    }

    fun setEmailCheckView(emailCheckView: EmailCheckView) {
        this.emailCheckView = emailCheckView
    }

    fun setVerificationCodeView(verificationCodeView: VerificationCodeView) {
        this.verificationCodeView = verificationCodeView
    }

    fun setPutExpertCourseView(putExpertCourseView: PutExpertCourseView) {
        this.putExpertCourseView = putExpertCourseView
    }


    // 회원가입
    fun join(user: User) {
        val joinService = getRetrofit().create(UsersRetrofitInterface::class.java)
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
        val joinService = getRetrofit().create(UsersRetrofitInterface::class.java)
        joinService.emailCheck(email).enqueue(object : Callback<EmailCheckResponse> {
            override fun onResponse(
                call: Call<EmailCheckResponse>,
                response: Response<EmailCheckResponse>
            ) {
                Log.d("EMAIL_CHECK/SUCCESS", response.toString())

                val resp: EmailCheckResponse = response.body()!!

                if (resp.isSuccess) {
                    when (resp.result) {
                        false -> emailCheckView.onEmailCheckSuccess()  // 중복 X
                        true -> emailCheckView.onEmailCheckFailure("이미 회원가입된 이메일입니다.")  // 중복 O
                    }
                } else {
                    when (resp.code) {
                        2015 -> emailCheckView.onEmailCheckFailure("올바른 이메일을 입력해주세요.")
                        2016, 4000 -> emailCheckView.onEmailCheckFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<EmailCheckResponse>, t: Throwable) {
                Log.d("EMAIL_CHECK/FAILURE", t.message.toString())
            }
        })
    }


    // 본인인증
    fun verificationCode(phone: String) {
        val joinService = getRetrofit().create(UsersRetrofitInterface::class.java)
        joinService.verificationCode(phone).enqueue(object : Callback<VerificationCodeResponse> {
            override fun onResponse(
                call: Call<VerificationCodeResponse>,
                response: Response<VerificationCodeResponse>
            ) {
                Log.d("VERIFICATION_CODE/SUCCESS", response.toString())

                val resp: VerificationCodeResponse = response.body()!!

                // 서버 response 중 code 값에 따른 결과
                when (resp.code) {
                    1000 -> verificationCodeView.onVerificationCodeSuccess(resp.result)
                    else -> verificationCodeView.onVerificationCodeFailure(resp.message)
                }

            }

            override fun onFailure(call: Call<VerificationCodeResponse>, t: Throwable) {
                Log.d("VERIFICATION_CODE/FAILURE", t.message.toString())
            }
        })
    }


    // 전문가 코스 목록에 담기
    fun putExpertCourse(jwt: String, userIdx: Int, expertIdx: Int) {
        val joinService = getRetrofit().create(UsersRetrofitInterface::class.java)
        joinService.putExpertCourse(jwt, userIdx, expertIdx)
            .enqueue(object : Callback<PutExpertCourseResponse> {
                override fun onResponse(
                    call: Call<PutExpertCourseResponse>,
                    response: Response<PutExpertCourseResponse>
                ) {
                    Log.d("PUT_EXPERT_COURSE/SUCCESS", response.toString())

                    val resp: PutExpertCourseResponse = response.body()!!

                    // 서버 response 중 code 값에 따른 결과
                    when (resp.code) {
                        1000 -> putExpertCourseView.onPutExpertCourseSuccess()
                        else -> putExpertCourseView.onPutExpertCourseFailure(resp.code, resp.message)
                    }

                }

                override fun onFailure(call: Call<PutExpertCourseResponse>, t: Throwable) {
                    Log.d("PUT_EXPERT_COURSE/FAILURE", t.message.toString())
                }
            })
    }
}