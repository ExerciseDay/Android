package com.example.exerciseday_android

data class SaveCourseRes (
    val isSuccess : Boolean,
    val code : Int,
    val message: String,
    val result: SaveCourseResult
)

data class SaveCourseResult(
    val customIdx: Int,
    val userIdx: Int
)
