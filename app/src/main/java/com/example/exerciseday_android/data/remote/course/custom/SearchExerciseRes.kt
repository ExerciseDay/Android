package com.example.exerciseday_android

data class SearchExerciseRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: SearchExerciseList
)

data class SearchExerciseList(
    val count: Int,
    val exercises: ArrayList<SearchExercise>
)

data class SearchExercise(
    val exerciseIdx: Int,
    val exerciseName: String,
    val exercisePart: String,
    val exerciseDetailPart: String,
    val exerciseIntroduce: String
)