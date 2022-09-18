package com.example.exerciseday_android.data.remote

data class WithdrawRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String,
)