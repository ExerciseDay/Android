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

    @POST("/users/findPwd")
    fun findPw(
        @Body postFindPw: PostFindPw
    ): Call<FindPwResponse>

    @PATCH("/users/edit/pwd")
    fun editPw(
        @Header("X-ACCESS-TOKEN") token: String,
        @Body patchEditPw: PatchEditPw
    ): Call<NewPwResponse>

    @GET("/exercise/search")
    fun searchExercise(
        @Query("what") what : String,
    ): Call<SearchExerciseRes>

    @GET("/exercise/{exerciseIdx}")
    fun getExercise(
        @Path("exerciseIdx") num : Int,
    ): Call<ExerciseRes>

    @GET("/users/{userIdx}/course")
    fun getCourse(
        @Header("X-ACCESS-TOKEN") token: String,
        @Path("userIdx") userIdx : Int,
    ): Call<GetCourseRes>
}

data class PostLogin(
    val email: String,
    val password: String
)

data class PostFindPw(
    val email: String,
    val phone: String
)

data class PatchEditPw(
    val userIdx: Int,
    val password: String
)
