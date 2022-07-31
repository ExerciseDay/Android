package com.example.exerciseday_android

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "ec9e0ae841a16206f3d0144f0b6b07f2")
    }
}