package com.example.exerciseday_android

interface JoinView {
    fun onJoinSuccess()
    fun onJoinFailure(code: Int, message: String)
}