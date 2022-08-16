package com.example.exerciseday_android.data.remote.auth

import com.example.exerciseday_android.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {
    @POST("/users")
    fun join(@Body user: User): Call<JoinResponse>

    @GET("/users/check/email")
    fun emailCheck(@Query("email") email: String): Call<EmailCheckResponse>

    @POST("/users/sms")
    fun verificationCode(@Query("phone") phone: String): Call<VerificationCodeResponse>
}