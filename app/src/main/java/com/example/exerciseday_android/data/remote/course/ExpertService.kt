package com.example.exerciseday_android.data.remote.course

import android.util.Log
import com.example.exerciseday_android.CheckExpertView
import com.example.exerciseday_android.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExpertService {
    private lateinit var checkExpertView: CheckExpertView

    fun setCheckExpertView(checkExpertView: CheckExpertView) {
        this.checkExpertView = checkExpertView
    }

    // 전문가 코스 조회
    fun checkExpert(expertIdx: Int) {
        val joinService = getRetrofit().create(ExpertRetrofitInterface::class.java)
        joinService.checkExpert(expertIdx).enqueue(object : Callback<ExpertResponse> {
            override fun onResponse(
                call: Call<ExpertResponse>,
                response: Response<ExpertResponse>
            ) {
                Log.d("CHECK_EXPERT/SUCCESS", response.toString())

                val resp: ExpertResponse = response.body()!!

                when (resp.code) {
                    1000 -> checkExpertView.onCheckExpertSuccess(resp.result.expertNTC, resp.result.expertRoutineInfos)
                    else -> checkExpertView.onCheckExpertFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<ExpertResponse>, t: Throwable) {
                Log.d("CHECK_EXPERT/FAILURE", t.message.toString())
            }
        })
    }
}