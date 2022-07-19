package com.example.exerciseday_android

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "nickname") var nickname: String,
    @SerializedName(value = "email") var email: String,
    @SerializedName(value = "password") var password: String,
    @SerializedName(value = "path") var path: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
