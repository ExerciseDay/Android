package com.example.exerciseday_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.data.remote.course.ExpertNTC
import com.example.exerciseday_android.data.remote.course.ExpertRoutineInfos
import com.example.exerciseday_android.data.remote.course.ExpertService
import com.example.exerciseday_android.databinding.FragmentExpertCourseBinding


class ExpertCourseFragment : Fragment(), CheckExpertView {

    lateinit var binding: FragmentExpertCourseBinding
    private var expertRoutineInfosData = ArrayList<ExpertRoutineInfos>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpertCourseBinding.inflate(inflater, container, false)

        checkExpert()

        // 전문가 코스 RecyclerView 어댑터와 데이터 리스트 연결
        binding.expertCourseCheckRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val expertCourseRVAdapter = ExpertCourseRVAdapter(expertRoutineInfosData)

        expertCourseRVAdapter.setMyITemClickListener(object : ExpertCourseRVAdapter.MyItemClickListener {
            override fun onItemClick(expertRoutineInfos: ExpertRoutineInfos) {
                TODO("Not yet implemented")
                // Item 클릭 시 운동 세부 페이지로 이동

            }
        })

        binding.expertCourseCheckRv.adapter = expertCourseRVAdapter

        return binding.root
    }

    private fun checkExpert() {
        // 코스 메인에서 클릭한 전문가 코스의 expertIdx 받아와야함

//        Log.d("expertIdx", expertIdx.toString())
        val expertService = ExpertService()
        expertService.setCheckExpertView(this)

        expertService.checkExpert(0)  // 0 -> 클릭한 전문가 코스의 expertIdx
    }

    override fun onCheckExpertSuccess(resultNTC: ExpertNTC, resultRoutineInfo: ArrayList<ExpertRoutineInfos>) {
        expertRoutineInfosData = resultRoutineInfo

        binding.expertCourseCheckCourseTv.text = resultNTC.expertName
        binding.expertCourseCheckClockTv.text = resultNTC.expertTime.toString()
        binding.expertCourseCheckCalorieTv.text = resultNTC.expertCalory.toString()
    }


    override fun onCheckExpertFailure(code: Int, message: String) {
        Log.d("CHECK_EXPERT/FAILURE", "$code / $message")
    }

}

interface CheckExpertView {
    fun onCheckExpertSuccess(resultNTC: ExpertNTC, resultRoutineInfo: ArrayList<ExpertRoutineInfos>)
    fun onCheckExpertFailure(code: Int, message: String)
}