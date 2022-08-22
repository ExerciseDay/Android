package com.example.exerciseday_android.ui.expert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentSelectBodyDetailPartBinding


class SelectBodyDetailPartFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentSelectBodyDetailPartBinding
    private lateinit var bodyDetailData: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectBodyDetailPartBinding.inflate(inflater, container, false)


        // 선택한 신체 부위에 따른 세부 부위 리스트 가져오기
        loadBodyPart()

        // 세부 부위 RecyclerView 어댑터와 데이터 리스트 연결
        val bodyDetailPartRVAdapter = BodyDetailPartRVAdapter(bodyDetailData)
        binding.selectBodyDetailPartRv.adapter = bodyDetailPartRVAdapter
        binding.selectBodyDetailPartRv.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        bodyDetailPartRVAdapter.setMyItemClickListener(object : BodyDetailPartRVAdapter.MyItemClickListener {
            override fun onItemClick(bodyDetailPartList: String) {
                // 세부 부위 버튼 선택 시, 선택한 부위의 코스 조회 페이지로 이동
                sendBodyDetailPart(bodyDetailPartList)
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, CheckBodyExpertCourseFragment())
                    .commitAllowingStateLoss()
            }

        })

        // 뒤로 가기
        binding.selectBodyDetailPartBackBtn.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.selectBodyDetailPartBackBtn -> (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SelectBodyPartFragment())
                .commitAllowingStateLoss()
        }
    }

    private fun loadBodyPart() {
        var pref = this.activity?.getPreferences(0)
        var bodyPart = pref?.getString("bodyPart", "")

        if (bodyPart.equals("팔")) {
            bodyDetailData = resources.getStringArray(R.array.body_detail_part_arm_entries)
        } else if (bodyPart.equals("하체")) {
            bodyDetailData = resources.getStringArray(R.array.body_detail_part_lower_body_entries)
        } else if (bodyPart.equals("복근")) {
            bodyDetailData = resources.getStringArray(R.array.body_detail_part_abdomen_entries)
        } else if (bodyPart.equals("가슴")) {
            bodyDetailData = resources.getStringArray(R.array.body_detail_part_chest_entries)
        } else if (bodyPart.equals("어깨")) {
            bodyDetailData = resources.getStringArray(R.array.body_detail_part_shoulder_entries)
        } else if (bodyPart.equals("등")) {
            bodyDetailData = resources.getStringArray(R.array.body_detail_part_back_entries)
        } else {
            // 목 선택한 경우
            // 바로 운동 세부 페이지 이동

        }
    }

    // 선택한 신체 부위 전달
    private fun sendBodyDetailPart(bodyDetailPart: String) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putString("bodyDetailPart", bodyDetailPart)?.apply()
    }

}
