package com.example.exerciseday_android

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Result,
)

data class Result(
    val userIdx: Int,
    val jwt: String)
