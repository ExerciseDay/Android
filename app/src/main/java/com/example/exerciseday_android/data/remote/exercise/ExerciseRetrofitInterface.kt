package com.example.exerciseday_android.data.remote.exercise

import retrofit2.Call
import retrofit2.http.*

interface ExerciseRetrofitInterface {

    // 운동 조회
    @GET("/exercise/{exerciseIdx}")
    fun checkExercise(@Path("exerciseIdx") exerciseIdx: Int): Call<ExerciseResponse>

    // 운동 찜
    @POST("/exercise/{exerciseIdx}/dibs")
    fun likeExercise(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Path("exerciseIdx") exerciseIdx: Int,
        @Query("userIdx") userIdx: Int
    ): Call<LikeExerciseResponse>
}