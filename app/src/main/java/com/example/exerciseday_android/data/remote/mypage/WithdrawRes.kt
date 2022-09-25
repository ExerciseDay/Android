package com.example.exerciseday_android.data.remote.mypage

data class WithdrawRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String,
)