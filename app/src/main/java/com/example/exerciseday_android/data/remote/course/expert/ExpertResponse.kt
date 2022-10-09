package com.example.exerciseday_android.data.remote.course.expert

import com.google.gson.annotations.SerializedName

// 전문가 코스 조회
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
    @SerializedName("expertCalory") val expertCalory: Int
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
    @SerializedName("expertIdx") val expertIdx: Int
)

// 부위별 전문가 코스 목록 검색(조회)
data class ExpertPartCourseResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ExpertBodyPart
)

data class ExpertBodyPart(
    @SerializedName("part") val part: String,
    @SerializedName("detailPart") val detailPart: String,
    @SerializedName("expertList") val expertList: ArrayList<ExpertListInfo>
)

data class ExpertListInfo(
    @SerializedName("expertIdx") val expertIdx: Int,
    @SerializedName("expertName") val expertName: String,
    @SerializedName("expertIntroduce") val expertIntroduce: String,
    @SerializedName("expertTime") val expertTime: Int,
    @SerializedName("expertCalory") val expertCalory: Int,
    @SerializedName("part") val part: String,
    @SerializedName("detailPart") val detailPart: String
)