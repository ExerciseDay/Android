package com.example.exerciseday_android.data.remote.users

import com.example.exerciseday_android.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface UsersRetrofitInterface {

    // 회원가입
    @POST("/users")
    fun join(@Body user: User): Call<JoinResponse>

    // 이메일 중복 검사
    @GET("/users/check/email")
    fun emailCheck(@Query("email") email: String): Call<EmailCheckResponse>

    // 본인인증
    @POST("/users/sms")
    fun verificationCode(@Query("phone") phone: String): Call<VerificationCodeResponse>

    // 전문가 코스 목록에 담기
    @POST("/users/{userIdx}/expert/{expertIdx}")
    fun putExpertCourse(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Path("userIdx") userIdx: Int,
        @Path("expertIdx") expertIdx: Int
        ): Call<PutExpertCourseResponse>

}