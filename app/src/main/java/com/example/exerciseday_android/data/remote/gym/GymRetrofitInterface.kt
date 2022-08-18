package com.example.exerciseday_android.data.remote.gym

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GymRetrofitInterface {
    @GET("/gyms/univ")
    fun getGym(@Query("univ") univ: String): Call<GymResponse>

    // NaverMapAPI
    @Headers(
        "X-NCP-APIGW-API-KEY-ID: daz8baow16",
        "X-NCP-APIGW-API-KEY: daBGJ29BrW4P6ltVUfYt40lSwDqt9K1AIasx0UCE"
    )
    @GET("/map-geocode/v2/geocode")
    fun searchAddress(@Query("query") query: String): Call<AddressResponse>
}