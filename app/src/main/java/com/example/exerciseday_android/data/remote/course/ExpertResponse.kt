package com.example.exerciseday_android.data.remote.course

import com.google.gson.annotations.SerializedName

data class ExpertResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ExpertResult
)

data class ExpertResult(
    @SerializedName("expertNTC") val expertNTC: ExpertNTC,
    @SerializedName("expertRoutineInfos") val expertRoutineInfos: ArrayList<ExpertRoutineInfos>
)

data class ExpertNTC(
    @SerializedName("expertIdx") val expertIdx: Int,
    @SerializedName("expertName") val expertName: String,
    @SerializedName("expertTime") val expertTime: Int,
    @SerializedName("expertCalory") val expertCalory: Int,
)

data class ExpertRoutineInfos(
    @SerializedName("expertRoutineIdx") val expertRoutineIdx: Int,
    @SerializedName("exerciseName") val exerciseName: String,
    @SerializedName("exercisePart") val exercisePart: String,
    @SerializedName("exerciseDetailPart") val exerciseDetailPart: String,
    @SerializedName("exerciseInfo") val exerciseInfo: String,
    @SerializedName("exerciseImg") val exerciseImg: String,
    @SerializedName("exerciseTime") val exerciseTime: Int,
    @SerializedName("exerciseCalory") val exerciseCalory: Int,
    @SerializedName("exerciseIntroduce") val exerciseIntroduce: String,
    @SerializedName("expertIdx") val expertIdx: Int,
)