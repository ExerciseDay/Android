package com.example.exerciseday_android

data class GetCourseRes(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: GetCourseResult
)

data class GetCourseResult(
    val userIdx: Int,
    val userNickName: String,
    val userImg: String,
    val userGoal: String,
    val customList: ArrayList<Custom>,
    val expertList: ArrayList<Expert>
)

data class Custom(
    val customIdx: Int,
    val customName: String,
    val customTime: Int,
    val customCalory: Int,
)

data class Expert(
    val expertIdx: Int,
    val expertName: String,
    val expertTime: Int,
    val expertCalory: Int,
)