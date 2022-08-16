package com.example.exerciseday_android.data.remote.auth

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

// 이메일 중복
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