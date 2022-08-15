package com.example.exerciseday_android

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GymRetrofitInterface {
    @GET("/gyms/univ")
//    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    fun getGym(@Query("univ") univ: String): Call<GymResponse>
}