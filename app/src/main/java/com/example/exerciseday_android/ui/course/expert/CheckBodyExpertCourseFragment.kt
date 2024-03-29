package com.example.exerciseday_android.ui.course.expert

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.remote.course.expert.ExpertListInfo
import com.example.exerciseday_android.data.remote.course.expert.ExpertService
import com.example.exerciseday_android.databinding.FragmentCheckBodyExpertCourseBinding


class CheckBodyExpertCourseFragment : Fragment(), CheckExpertBodyCourseView, View.OnClickListener {

    lateinit var binding: FragmentCheckBodyExpertCourseBinding
    private var expertListInfoData = ArrayList<ExpertListInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckBodyExpertCourseBinding.inflate(inflater, container, false)

        binding.checkBodyExpertCourseRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val expertCourseRVAdapter = ExpertCourseRVAdapter(expertListInfoData)
        binding.checkBodyExpertCourseRv.adapter = expertCourseRVAdapter


        checkExpertPartCourse(loadAllBodyPart())

        // 뒤로 가기
        binding.checkBodyExpertCourseBackBtn.setOnClickListener(this)


        return binding.root
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.checkBodyExpertCourseBackBtn -> (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                .commitAllowingStateLoss()
        }
    }

    private fun checkExpertPartCourse(bodyArray: ArrayList<String>) {
        val expertService = ExpertService()
        expertService.setCheckExpertBodyCourseView(this)

        expertService.checkExpertPartCourse(bodyArray[0], bodyArray[1])
    }

    override fun onCheckExpertBodyCourseSuccess(
        resultExpertListInfo: ArrayList<ExpertListInfo>,
        part: String,
        detailPart: String
    ) {
        expertListInfoData = resultExpertListInfo

        binding.checkBodyExpertCourseRecommendCountContentTv.text = "${resultExpertListInfo.size}"


        // 부위별 전문가 코스 RecyclerView 어댑터와 데이터 리스트 연결
        binding.checkBodyExpertCourseRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val expertCourseRVAdapter = ExpertCourseRVAdapter(expertListInfoData)

        expertCourseRVAdapter.setMyItemClickListener(object :
            ExpertCourseRVAdapter.MyItemClickListener {
            override fun onItemClick(expertListInfo: ExpertListInfo) {
                // Item 클릭 시 전문가 코스 담기 페이지로 이동
                sendAllBodyPart(loadAllBodyPart()[0], loadAllBodyPart()[1])
                sendExpertCourseIdx(expertListInfo.expertIdx)
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, PutExpertFragment())
                    .commitAllowingStateLoss()
            }
        })

        binding.checkBodyExpertCourseRv.adapter = expertCourseRVAdapter
    }

    override fun onCheckExpertBodyCourseFailure(code: Int, message: String) {
        Log.d("CHECK_EXPERT_BODY_COURSE/FAILURE", "$code / $message")
    }


    private fun loadAllBodyPart(): ArrayList<String> {
        val pref = this.activity?.getPreferences(0)
        val bodyPart = pref?.getString("bodyPart", "").toString()
        val bodyDetailPart = pref?.getString("bodyDetailPart", "").toString()

        val bodyArray = arrayListOf(bodyPart, bodyDetailPart)

        binding.checkBodyExpertCourseBodyPartTv.text = bodyPart
        binding.checkBodyExpertCourseBodyDetailPartTv.text = bodyDetailPart

        return bodyArray
    }

    private fun sendExpertCourseIdx(expertIdx: Int) {
        val pref = this.activity?.getPreferences(0)
        val editor = pref?.edit()

        editor?.putInt("expertIdx", expertIdx)?.apply()
    }

    private fun sendAllBodyPart(bodyPart: String, bodyDetailPart: String) {
        val pref = this.activity?.getPreferences(0)
        val editor = pref?.edit()

        editor?.putString("bodyPart", bodyPart)?.apply()
        editor?.putString("bodyDetailPart", bodyDetailPart)?.apply()
    }



}

interface CheckExpertBodyCourseView {
    fun onCheckExpertBodyCourseSuccess(
        resultExpertListInfo: ArrayList<ExpertListInfo>,
        part: String,
        detailPart: String
    )

    fun onCheckExpertBodyCourseFailure(code: Int, message: String)
}