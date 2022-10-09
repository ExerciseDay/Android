package com.example.exerciseday_android.ui.course.expert

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.remote.course.expert.ExpertResponse
import com.example.exerciseday_android.data.remote.course.expert.ExpertRetrofitInterface
import com.example.exerciseday_android.data.remote.course.expert.ExpertRoutineInfos
import com.example.exerciseday_android.data.remote.exercise.ExerciseService
import com.example.exerciseday_android.databinding.FragmentExpertExerciseInfoBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ExpertExerciseInfoFragment : Fragment(), LikeExerciseView {

    lateinit var binding: FragmentExpertExerciseInfoBinding
    private val exerciseTab = arrayListOf("추천 옵션", "운동 방법")
    lateinit var expertRoutineInfosData: ArrayList<ExpertRoutineInfos>
    lateinit var realExpertRoutineInfosData: ExpertRoutineInfos

    var isLiked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpertExerciseInfoBinding.inflate(inflater, container, false)


        // 운동 조회 (PutExpertFragment에서 expertIdx 받아와야 함)
        checkExpert(loadExpertIdx())

        // [전문가 코스] 운동 세부 페이지 - TabLayout, ViewPager2 연결
        val expertExerciseFm = childFragmentManager
        val expertExerciseLifecycle = viewLifecycleOwner.lifecycle
        val expertExerciseVPAdapter =
            ExpertExerciseVPAdapter(expertExerciseFm, expertExerciseLifecycle)
        binding.expertExerciseVp.adapter = expertExerciseVPAdapter

        TabLayoutMediator(binding.expertExerciseTb, binding.expertExerciseVp) { tab, position ->
            tab.text = exerciseTab[position]
        }.attach()

        // 좋아요 버튼
        binding.expertExerciseLikeBtn.setOnClickListener {
            if (!isLiked) {
                binding.expertExerciseLikeBtn.setImageResource(R.drawable.ic_like_on)
                isLiked = true
//                likeExercise(loadUserJwt(), (), loadUserIdx())
            } else {
                binding.expertExerciseLikeBtn.setImageResource(R.drawable.ic_like_off)
                isLiked = false
            }
        }

        // 뒤로 가기
        binding.expertExerciseBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, PutExpertFragment())
                .commitAllowingStateLoss()
        }


        return binding.root
    }

    private fun checkExpert(expertIdx:Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: ExpertRetrofitInterface = retrofit.create(ExpertRetrofitInterface::class.java)

        server.checkExpert(expertIdx)
            .enqueue(object : Callback<ExpertResponse> {
                override fun onResponse(
                    call: Call<ExpertResponse>,
                    response: Response<ExpertResponse>
                ) {
                    val resp: ExpertResponse = response.body()!!
                    expertRoutineInfosData = resp.result.expertRoutineInfos


                    // 선택한 운동의 인포 가져오기
                    for (i in expertRoutineInfosData.indices) {
                        if (expertRoutineInfosData[i].expertRoutineIdx == loadExpertRoutineIdx()) {
                            realExpertRoutineInfosData = expertRoutineInfosData[i]
                        }
                    }

//                    Glide.with(this@ExpertExerciseInfoFragment).load("http://3.39.184.186:8080" + realExpertRoutineInfosData.exerciseImg).into(binding.expertExerciseImgIv)
                    binding.expertExerciseNameTv.text = realExpertRoutineInfosData.exerciseName
                    binding.expertExerciseIntroTv.text = realExpertRoutineInfosData.exerciseIntroduce
                    binding.expertExerciseBodyPartTv.text = realExpertRoutineInfosData.exercisePart
                    binding.expertExerciseBodyDetailPartTv.text = realExpertRoutineInfosData.exerciseDetailPart

                    Log.d("CHECK_EXPERT_PART_COURSE/SUCCESS", response.toString())
                }

                override fun onFailure(call: Call<ExpertResponse>, t: Throwable) {
                    Log.d("CHECK_EXPERT_PART_COURSE/FAILURE", t.toString())
                }
            })

    }


    private fun likeExercise(jwt:String, exerciseIdx: Int, userIdx: Int) {
        val exerciseService = ExerciseService()
        exerciseService.setLikeExerciseView(this)

//        exerciseService.likeExercise(jwt, exerciseIdx, userIdx)
    }

    override fun onLikeExerciseSuccess() {
    }

    override fun onLikeExerciseFailure(code: Int, message: String) {
    }


    private fun loadExpertIdx(): Int {
        var pref = this.activity?.getPreferences(0)
        var expertIdx = pref?.getInt("expertIdx", 0)!!.toInt()

        return expertIdx
    }

    private fun loadExpertRoutineIdx(): Int {
        var pref = this.activity?.getPreferences(0)
        var expertRoutineIdx = pref?.getInt("expertRoutineIdx", 0)!!.toInt()

        return expertRoutineIdx
    }

    private fun loadUserJwt(): String {
        var pref = this.activity?.getSharedPreferences("jwt", 0)
        var jwt = pref?.getString("jwt", "").toString()

        return jwt
    }

    private fun loadUserIdx(): Int {
        var pref = this.activity?.getSharedPreferences("userIdx", 0)
        var userIdx = pref?.getInt("userIdx", 0)!!.toInt()

        return userIdx
    }

}

interface LikeExerciseView {
    fun onLikeExerciseSuccess()
    fun onLikeExerciseFailure(code: Int, message: String)
}