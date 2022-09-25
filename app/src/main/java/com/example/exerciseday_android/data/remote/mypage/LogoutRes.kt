package com.example.exerciseday_android.data.remote.mypage

import com.example.exerciseday_android.LoginResult

data class LogoutRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String,
)