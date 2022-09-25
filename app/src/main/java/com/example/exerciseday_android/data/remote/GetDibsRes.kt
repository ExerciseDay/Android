package com.example.exerciseday_android.data.remote

data class GetDibsRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: DibsResult,
)

data class DibsResult(
    val userIdx: Int,
    val userNickname: String,
    val numOfDibs: Int,
    val exercises: ArrayList<DibsExercise>,
)

data class DibsExercise(
    val exerciseIdx: Int,
    val exerciseName: String,
    val exercisePart: String,
    val exerciseDetailPart: String,
    val exerciseIntroduce: String,
)
