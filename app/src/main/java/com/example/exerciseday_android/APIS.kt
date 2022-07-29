package com.example.exerciseday_android

import retrofit2.Call
import retrofit2.http.*

interface APIS {
//    @FormUrlEncoded
//    @Headers("accept: application/json", "content-type: application/json")
    @POST("/auth/login")
    fun login(
        @Body postLogin: PostLogin
    ): Call<LoginResponse>

//    @Headers("accept: application/json",
//        "content-type: application/json")


}

data class PostLogin(
    val email: String,
    val password: String
)

