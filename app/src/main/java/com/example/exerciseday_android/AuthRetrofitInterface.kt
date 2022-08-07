package com.example.exerciseday_android

import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {
    @POST("/users")
    fun join(@Body user: User): Call<JoinResponse>

    @GET("/users/check/email/{email}")
    fun emailCheck(@Path("email") email: String): Call<EmailCheckResponse>
}