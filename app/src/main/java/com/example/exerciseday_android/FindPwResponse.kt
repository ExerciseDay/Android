package com.example.exerciseday_android

data class FindPwResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: FindPwResult
)

data class FindPwResult(
    val userIdx: Int,
    val jwt: String
)

