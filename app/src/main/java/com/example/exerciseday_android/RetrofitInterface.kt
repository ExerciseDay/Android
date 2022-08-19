package com.example.exerciseday_android

import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("/gyms/univ")
    fun getGymDetail(
        @Query("univ") univ: String
    ): Call<GymDetailResponse>
}
