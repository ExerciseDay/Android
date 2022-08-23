package com.example.exerciseday_android

data class ExerciseRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ExerciseResult,
)

data class ExerciseResult(
    val exerciseIdx: Int,
    val exerciseName: String,
    val exercisePart: String,
    val exerciseDetailPart: String,
    val exerciseInfo: String,
    val exerciseImg: String,
    val exerciseTime: Int,
    val exerciseCalory: Int,
    val exerciseIntroduce: String,
)