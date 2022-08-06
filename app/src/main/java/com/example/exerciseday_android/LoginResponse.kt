package com.example.exerciseday_android

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: LoginResult,
)

data class LoginResult(
    val userIdx: Int,
    val jwt: String)
