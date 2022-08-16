package com.example.exerciseday_android.data.remote.gym

import com.google.gson.annotations.SerializedName

data class GymResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<GymMainResult>
)

data class GymMainResult(
    @SerializedName("gymIdx") var gymIdx: Int,
    @SerializedName("gymName") var gymName: String,
    @SerializedName("gymIntroduce") var gymIntroduce: String,
    @SerializedName("gymImg") var gymImg: String,
    @SerializedName("gymDistance") var gymDistance: Int,
    @SerializedName("univ") var univ: String,
    @SerializedName("gymParking") var gymParking: Boolean,
    @SerializedName("gymSauna") var gymSauna: Boolean,
    @SerializedName("gymCloths") var gymCloths: Boolean,
    @SerializedName("gymShower") var gymShower: Boolean,
)