package com.example.exerciseday_android.data.remote.gym

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GymRetrofitInterface {
    @GET("/gyms/univ")
//    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    fun getGym(@Query("univ") univ: String): Call<GymResponse>
}