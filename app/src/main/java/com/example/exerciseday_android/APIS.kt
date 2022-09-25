package com.example.exerciseday_android

import com.example.exerciseday_android.data.remote.GetDibsRes
import com.example.exerciseday_android.data.remote.mypage.WithdrawRes
import com.example.exerciseday_android.data.remote.find.NewPwResponse
import com.example.exerciseday_android.data.remote.mypage.LogoutRes
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

    @POST("/users/{userIdx}/custom")
    fun saveCourse(
        @Header("X-ACCESS-TOKEN") token: String,
        @Path("userIdx") userIdx : Int,
        @Body saveCourseBody: SaveCourseBody
    ): Call<SaveCourseRes>

    @PATCH("/auth/logout/{userIdx}")
    fun logout(
        @Header("X-ACCESS-TOKEN") token: String,
        @Path("userIdx") userIdx : Int,
    ): Call<LogoutRes>

    @PATCH("/users/delete/{userIdx}")
    fun withdraw(
        @Header("X-ACCESS-TOKEN") token: String,
        @Path("userIdx") userIdx : Int,
    ): Call<WithdrawRes>

    @GET("/exercise/dibs")
    fun getDibs(
        @Header("X-ACCESS-TOKEN") token: String,
        @Query("userIdx") userIdx: Int,
    ): Call<GetDibsRes>
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

data class SaveCourseBody(
    val customName: String,
    val customIntroduce: String,
    val exercises: ArrayList<Course2>
)

data class Course2(
    val exerciseIdx: Int,
    val rep: Int,
    val weight: Int,
    val set: Int,
)