package com.example.exerciseday_android.data.remote.exercise

import com.google.gson.annotations.SerializedName


data class ExerciseResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ExerciseInfoResult
)

data class ExerciseInfoResult(
    @SerializedName("exerciseIdx") val exerciseIdx: Int,
    @SerializedName("exerciseName") val exerciseName: String,
    @SerializedName("exercisePart") val exercisePart: String,
    @SerializedName("exerciseDetailPart") val exerciseDetailPart: String,
    @SerializedName("exerciseInfo") val exerciseInfo: String,
    @SerializedName("exerciseImg") val exerciseImg: String,
    @SerializedName("exerciseTime") val exerciseTime: Int,
    @SerializedName("exerciseCalory") val exerciseCalory: Int,
    @SerializedName("exerciseIntroduce") val exerciseIntroduce: String
)

// 운동 찜
data class LikeExerciseResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)