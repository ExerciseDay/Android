package com.example.exerciseday_android.data.remote.course.expert

import retrofit2.Call
import retrofit2.http.*

interface ExpertRetrofitInterface {

    // 전문가 코스 조회
    @GET("/expert/{expertIdx}")
    fun checkExpert(@Path("expertIdx") expertIdx: Int): Call<ExpertResponse>

    // 부위별 전문가 코스 목록 검색(조회)
    @GET("/expert/list")
    fun checkExpertPartCourse(
        @Query("part") part: String,
        @Query("detailPart") detailPart: String,
        @Query("page") page: Int = 1
    ): Call<ExpertPartCourseResponse>

}