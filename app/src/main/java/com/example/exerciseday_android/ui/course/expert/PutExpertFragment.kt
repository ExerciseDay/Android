package com.example.exerciseday_android.ui.course.expert

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.*
import com.example.exerciseday_android.data.remote.users.UsersService
import com.example.exerciseday_android.data.remote.course.*
import com.example.exerciseday_android.databinding.FragmentPutExpertBinding
import com.example.exerciseday_android.ui.DefaultDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PutExpertFragment : Fragment(), PutExpertCourseView, View.OnClickListener {

    lateinit var binding: FragmentPutExpertBinding
    private var expertRoutineInfosData = ArrayList<ExpertRoutineInfos>()
    private var realExpertRoutineInfosData = ArrayList<ExpertRoutineInfos>()

    private var expertListInfosData = ArrayList<ExpertListInfo>()
    lateinit var realExpertListInfosData: ExpertListInfo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPutExpertBinding.inflate(inflater, container, false)

        binding.putExpertExerciseRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val expertExerciseRVAdapter =
            ExpertExerciseRVAdapter(realExpertRoutineInfosData)
        binding.putExpertExerciseRv.adapter = expertExerciseRVAdapter


        checkExpertPartCourse()
        checkExpert()

        // 뒤로 가기
        binding.putExpertBackBtn.setOnClickListener(this)

        // 담기 버튼 클릭 시
        binding.putExpertPutBtn.setOnClickListener(this)


        return binding.root
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.putExpertBackBtn -> (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, CheckBodyExpertCourseFragment())
                .commitAllowingStateLoss()
            binding.putExpertPutBtn -> {
                // 담기
                Log.d("jwt", loadUserJwt())
                putExpertCourse(loadUserJwt(), loadUserIdx(), loadExpertIdx())
            }
        }
    }

    private fun putExpertCourse(jwt: String, userIdx: Int, expertIdx: Int) {
        val joinService = UsersService()
        joinService.setPutExpertCourseView(this)

        joinService.putExpertCourse(jwt, userIdx, expertIdx)
    }

    override fun onPutExpertCourseSuccess() {
        val dialog = DefaultDialog(context as MainActivity)
        dialog.show("담기가 완료되었습니다!")

    }

    override fun onPutExpertCourseFailure(code: Int, message: String) {
        val dialog = DefaultDialog(context as MainActivity)
        dialog.show(message)

        Log.d("PUT_EXPERT_COURSE/FAILURE", "$code / $message")
    }

    private fun checkExpertPartCourse() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: ExpertRetrofitInterface = retrofit.create(ExpertRetrofitInterface::class.java)

        server.checkExpertPartCourse(loadAllBodyPart()[0], loadAllBodyPart()[1])
            .enqueue(object : Callback<ExpertPartCourseResponse> {
                override fun onResponse(
                    call: Call<ExpertPartCourseResponse>,
                    response: Response<ExpertPartCourseResponse>
                ) {
                    Log.d("CHECK_EXPERT_PART_COURSE/SUCCESS", response.toString())

                    val resp: ExpertPartCourseResponse = response.body()!!
                    expertListInfosData = resp.result.expertList


                    // 선택한 전문가 코스의 코스 인포 가져오기
                    for (i in expertListInfosData.indices) {
                        if (expertListInfosData[i].expertIdx == loadExpertIdx()) {
                            realExpertListInfosData = expertListInfosData[i]
                        }
                    }

                    binding.putExpertCourseNameTv.text = realExpertListInfosData.expertName
                    binding.putExpertCourseIntroTv.text = realExpertListInfosData.expertIntroduce
                    binding.putExpertClockTv.text =
                        (realExpertListInfosData.expertTime / 60).toString() + "분"

                }

                override fun onFailure(call: Call<ExpertPartCourseResponse>, t: Throwable) {
                    Log.d("CHECK_EXPERT_PART_COURSE/FAILURE", t.toString())
                }
            })
    }

    private fun checkExpert() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: ExpertRetrofitInterface = retrofit.create(ExpertRetrofitInterface::class.java)

        server.checkExpert(loadExpertIdx())
            .enqueue(object : Callback<ExpertResponse> {
                override fun onResponse(
                    call: Call<ExpertResponse>,
                    response: Response<ExpertResponse>
                ) {
                    Log.d("CHECK_EXPERT/SUCCESS", response.toString())

                    val resp: ExpertResponse = response.body()!!
                    expertRoutineInfosData = resp.result.expertRoutineInfos


                    // 선택한 전문가 코스의 운동 인포 가져오기
                    for (i in expertRoutineInfosData.indices) {
                        if (expertRoutineInfosData[i].expertIdx == loadExpertIdx()) {
                            realExpertRoutineInfosData.add(expertRoutineInfosData[i])
                        }
                    }

                    // 선택한 전문가 코스의 운동 RecyclerView 어댑터와 데이터 리스트 연결
                    binding.putExpertExerciseRv.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    val expertExerciseRVAdapter =
                        ExpertExerciseRVAdapter(realExpertRoutineInfosData)

                    binding.putExpertExerciseRv.adapter = expertExerciseRVAdapter

                    expertExerciseRVAdapter.setMyITemClickListener(object :
                        ExpertExerciseRVAdapter.MyItemClickListener {
                        override fun onItemClick(realExpertRoutineInfosData: ExpertRoutineInfos) {
                            // Item 클릭 시 운동 세부 페이지로 이동
                            sendExpertIdx(realExpertRoutineInfosData.expertIdx)
                            sendExpertRoutineIdx(realExpertRoutineInfosData.expertRoutineIdx)
                            (context as MainActivity).supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, ExpertExerciseInfoFragment())
                                .commitAllowingStateLoss()
                        }
                    })

                }

                override fun onFailure(call: Call<ExpertResponse>, t: Throwable) {
                    Log.d("CHECK_EXPERT/FAILURE", t.toString())
                }
            })
    }

    private fun sendExpertRoutineIdx(expertRoutineIdx: Int) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putInt("expertRoutineIdx", expertRoutineIdx)?.apply()
    }

    private fun sendExpertIdx(expertIdx: Int) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putInt("expertIdx", expertIdx)?.apply()
    }

    private fun loadExpertIdx(): Int {
        var pref = this.activity?.getPreferences(0)
        var expertIdx = pref?.getInt("expertIdx", 0)!!.toInt()

        return expertIdx
    }

    private fun loadAllBodyPart(): ArrayList<String> {
        var pref = this.activity?.getPreferences(0)
        var bodyPart = pref?.getString("bodyPart", "").toString()
        var bodyDetailPart = pref?.getString("bodyDetailPart", "").toString()

        var bodyArray = arrayListOf(bodyPart, bodyDetailPart)

        return bodyArray
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

interface PutExpertCourseView {
    fun onPutExpertCourseSuccess()
    fun onPutExpertCourseFailure(code: Int, message: String)
}