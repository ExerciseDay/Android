package com.example.exerciseday_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/users")
    fun signUp(@Body user: User): Call<AuthResponse>
}