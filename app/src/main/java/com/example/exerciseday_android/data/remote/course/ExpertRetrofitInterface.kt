package com.example.exerciseday_android.data.remote.course

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExpertRetrofitInterface {
    @GET("/expert/{expertIdx}")
    fun checkExpert(@Path("expertIdx") expertIdx: Int): Call<ExpertResponse>
}