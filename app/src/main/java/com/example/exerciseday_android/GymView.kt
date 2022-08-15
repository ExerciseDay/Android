package com.example.exerciseday_android

interface GymView {
    fun onGymSuccess(result: ArrayList<GymList>)
    fun onGymFailure(code: Int, message: String)
}