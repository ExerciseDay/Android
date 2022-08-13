package com.example.exerciseday_android

import retrofit2.Call
import retrofit2.http.*

interface APIS {
    @POST("/auth/login")
    fun login(
        @Body postLogin: PostLogin
    ): Call<LoginResponse>

    @GET("/users/findId")
    fun findId(
        @Query("phone") phone : String,
    ): Call<FindIdResponse>
}

data class PostLogin(
    val email: String,
    val password: String
)

data class GetFindId(
    val phone: String,
)

