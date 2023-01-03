package com.example.exerciseday_android.data.remote.course.expert

import android.util.Log
import com.example.exerciseday_android.CheckExpertView
import com.example.exerciseday_android.getRetrofit
import com.example.exerciseday_android.ui.course.expert.CheckExpertBodyCourseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExpertService {
    private lateinit var checkExpertView: CheckExpertView
    private lateinit var checkExpertBodyCourseView: CheckExpertBodyCourseView

    fun setCheckExpertView(checkExpertView: CheckExpertView) {
        this.checkExpertView = checkExpertView
    }

    fun setCheckExpertBodyCourseView(checkExpertBodyCourseView: CheckExpertBodyCourseView) {
        this.checkExpertBodyCourseView = checkExpertBodyCourseView
    }

    // 전문가 코스 조회
    fun checkExpert(expertIdx: Int) {
        val expertService = getRetrofit().create(ExpertRetrofitInterface::class.java)
        expertService.checkExpert(expertIdx).enqueue(object : Callback<ExpertResponse> {
            override fun onResponse(
                call: Call<ExpertResponse>,
                response: Response<ExpertResponse>
            ) {
                Log.d("CHECK_EXPERT/SUCCESS", response.toString())

                val resp: ExpertResponse = response.body()!!

                when (resp.code) {
                    1000 -> checkExpertView.onCheckExpertSuccess(
                        resp.result.expertNTC,
                        resp.result.expertRoutineInfos
                    )
                    else -> checkExpertView.onCheckExpertFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<ExpertResponse>, t: Throwable) {
                Log.d("CHECK_EXPERT/FAILURE", t.message.toString())
            }
        })
    }


    // 부위별 전문가 코스 목록 검색(조회)
    fun checkExpertPartCourse(part: String, detailPart: String) {
        val expertService = getRetrofit().create(ExpertRetrofitInterface::class.java)
        expertService.checkExpertPartCourse(part, detailPart)
            .enqueue(object : Callback<ExpertPartCourseResponse> {
                override fun onResponse(
                    call: Call<ExpertPartCourseResponse>,
                    response: Response<ExpertPartCourseResponse>
                ) {
                    Log.d("CHECK_EXPERT_PART_COURSE/SUCCESS", response.toString())

                    val resp: ExpertPartCourseResponse = response.body()!!

                    when (resp.code) {
                        1000 -> checkExpertBodyCourseView.onCheckExpertBodyCourseSuccess(
                            resp.result.expertList,
                            resp.result.expertList[0].part,
                            resp.result.expertList[0].detailPart
                        )
                        else -> checkExpertBodyCourseView.onCheckExpertBodyCourseFailure(
                            resp.code,
                            resp.message
                        )
                    }
                }

                override fun onFailure(call: Call<ExpertPartCourseResponse>, t: Throwable) {
                    Log.d("CHECK_EXPERT_PART_COURSE/FAILURE", t.message.toString())
                }
            })
    }
}