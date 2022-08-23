package com.example.exerciseday_android.data.model
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "email") var email: String,
    @SerializedName(value = "password") var password: String,
    @SerializedName(value = "nickname") var nickname: String,
    @SerializedName(value = "phone") var phone: String,
    @SerializedName(value = "gender") var gender: String,
    @SerializedName(value = "goal") var goal: String
)