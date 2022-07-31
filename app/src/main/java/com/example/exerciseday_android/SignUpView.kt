package com.example.exerciseday_android

interface SignUpView {
    fun onSignUpSuccess()
    fun onSignUpFailure(code: Int, message: String)
}