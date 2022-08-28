package com.example.exerciseday_android.data.remote.exercise

import com.example.exerciseday_android.ui.course.expert.LikeExerciseView

class ExerciseService {
    private lateinit var likeExerciseView: LikeExerciseView

    fun setLikeExerciseView(likeExerciseView: LikeExerciseView) {
        this.likeExerciseView = likeExerciseView
    }


    // 운동 찜
//    fun likeExercise(jwt:String, exerciseIdx: Int, userIdx:Int) {
//        val exerciseService = getRetrofit().create(ExerciseRetrofitInterface::class.java)
//        exerciseService.likeExercise(jwt, exerciseIdx, userIdx).enqueue(object : Callback<LikeExerciseResponse> {
//            override fun onResponse(
//                call: Call<LikeExerciseResponse>,
//                response: Response<LikeExerciseResponse>
//            ) {
//                Log.d("LIKE_EXERCISE/SUCCESS", response.toString())
//
//                val resp: LikeExerciseResponse = response.body()!!
//
//                if (resp.isSuccess) {
//                    likeExerciseView.onLikeExerciseSuccess()
//                } else {
//                    likeExerciseView.onLikeExerciseFailure(resp.code, resp.message)
//                }
//            }
//
//            override fun onFailure(call: Call<LikeExerciseResponse>, t: Throwable) {
//                Log.d("LIKE_EXERCISE/FAILURE", t.message.toString())
//            }
//        })
//    }
}