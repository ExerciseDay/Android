package com.example.exerciseday_android

interface EmailCheckView {
    fun onEmailCheckSuccess()
    fun onEmailCheckFailure(message: String)
}