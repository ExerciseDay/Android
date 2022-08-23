package com.example.exerciseday_android.data.remote.users

import com.google.gson.annotations.SerializedName

data class JoinResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: JoinResult
)

data class JoinResult(
    @SerializedName("userIdx") var userIdx: Int,
    @SerializedName("jwt") var jwt: String
)

// 이메일 중복 검사
data class EmailCheckResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Boolean
)

// 본인인증
data class VerificationCodeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)

// 전문가 코스 목록에 담기
data class PutExpertCourseResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: PutExpertCourseResult
)

data class PutExpertCourseResult(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("expertId") val expertId: Int
)