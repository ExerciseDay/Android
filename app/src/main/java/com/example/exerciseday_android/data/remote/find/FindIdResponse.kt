package com.example.exerciseday_android

import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

data class FindIdResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: FindIdResult
)

data class FindIdResult(
    val userIdx: Int,
    val email: String,
    val userCreate: String
)