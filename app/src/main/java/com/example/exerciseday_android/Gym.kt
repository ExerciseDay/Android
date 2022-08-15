package com.example.exerciseday_android

import com.google.gson.annotations.SerializedName

data class Gym(
    @SerializedName(value = "gymName") var gymName: String,
//    @SerializedName(value = "gymAddress") var gymAddress: String,
    @SerializedName(value = "gymIntroduce") var gymIntroduce: String,
    @SerializedName(value = "gymImg") var gymImg: String,
    @SerializedName(value = "gymDistance") var gymDistance: Int,
    @SerializedName(value = "gymUniv") var gymUniv: String,

    @SerializedName(value = "gymParking") var gymParking: Boolean,
    @SerializedName(value = "gymSauna") var gymSauna: Boolean,
    @SerializedName(value = "gymCloths") var gymCloths: Boolean,
    @SerializedName(value = "gymShower") var gymShower: Boolean
)