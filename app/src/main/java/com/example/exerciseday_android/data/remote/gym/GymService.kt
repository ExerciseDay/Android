package com.example.exerciseday_android.data.remote.gym

import android.util.Log
import com.example.exerciseday_android.GymView
import com.example.exerciseday_android.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GymService {
    private lateinit var gymView: GymView

    fun setGymView(gymView: GymView) {
        this.gymView = gymView
    }

    // 헬스장 리스트
    fun getGym(univ: String) {
        val gymService = getRetrofit().create(GymRetrofitInterface::class.java)
        gymService.getGym(univ).enqueue(object : Callback<GymResponse> {
            override fun onResponse(call: Call<GymResponse>, response: Response<GymResponse>) {
                Log.d("GET_GYM/SUCCESS", response.toString())

//                val resp: GymResponse = response.body()!!

//                Log.d("GET_GYM/CODE", resp.code.toString())

                // 서버 response 중 code 값에 따른 결과
                if (response.isSuccessful) {

                } else {
                    Log.d("GET_GYM/CODE", response.code().toString())
                }
//                when (resp.code) {
//                    1000 -> gymView.onGymSuccess(resp.result)
//                    else -> gymView.onGymFailure(resp.code, resp.message)
//                }
            }

            override fun onFailure(call: Call<GymResponse>, t: Throwable) {
                Log.d("GET_GYM/FAILURE", t.message.toString())
            }
        })
        Log.d("GET_GYM", "HELLO")
    }
}