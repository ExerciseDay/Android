package com.example.exerciseday_android

interface JoinView {
    fun onJoinSuccess()
    fun onJoinFailure(code: Int, message: String)
}

interface EmailCheckView {
    fun onEmailCheckSuccess()
    fun onEmailCheckFailure(message: String)
}

interface VerificationCodeView {
    fun onVerificationCodeSuccess(result: String)
    fun onVerificationCodeFailure(message: String)
}