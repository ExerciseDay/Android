package com.example.exerciseday_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.model.GymReview
import com.example.exerciseday_android.data.model.MyReview
import com.example.exerciseday_android.databinding.FragmentGymInfoBinding
import com.example.exerciseday_android.databinding.FragmentMyReviewBinding
import com.example.exerciseday_android.ui.gym.detail.GymReviewRVAdapter


class MyReviewFragment : Fragment() {

    lateinit var binding: FragmentMyReviewBinding
    private var myReviewData = ArrayList<MyReview>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyReviewBinding.inflate(inflater, container, false)


        // 뒤로 가기
        binding.myReviewBackBtn.setOnClickListener {
            // 마이페이지 메인으로 이동
        }

        // 데이터 리스트 생성 더미 데이터
        myReviewData.apply {
            add(
                MyReview(
                    "작심삼일 PT",
                    "PT 받고 살 많이 빠진거같아요 추천!",
                    "2022/08/15",
                    3.0
                )
            )
            add(
                MyReview(
                    "하루운동 PT",
                    "트레이너님이 친절하고 좋아요~!",
                    "2022/08/15",
                    4.0
                )
            )
            add(
                MyReview(
                    "하루운동 PT",
                    "트레이너님이 친절하고 좋아요~!",
                    "2022/08/15",
                    4.0
                )
            )
            add(
                MyReview(
                    "하루운동 PT",
                    "트레이너님이 친절하고 좋아요~!",
                    "2022/08/15",
                    4.0
                )
            )
            add(
                MyReview(
                    "하루운동 PT",
                    "트레이너님이 친절하고 좋아요~!",
                    "2022/08/15",
                    4.0
                )
            )
        }

        // 리뷰 RecyclerView 어댑터와 데이터 리스트 연결
        if(myReviewData.size == 0) {
            binding.myReviewRv.visibility = View.GONE
            binding.myReviewInfoCl.visibility = View.GONE
            binding.myReviewEmptyLayout.visibility = View.VISIBLE
        } else {
            binding.myReviewEmptyLayout.visibility = View.GONE
            binding.myReviewInfoCl.visibility = View.VISIBLE
            binding.myReviewRv.visibility = View.VISIBLE
        }


        val myReviewRVAdapter = MyReviewRVAdapter(myReviewData)
        binding.myReviewRv.adapter = myReviewRVAdapter

        // 리뷰 RecyclerView 구분선
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.rv_devider, null)!!
        )
        binding.myReviewRv.addItemDecoration(dividerItemDecoration)



        return binding.root
    }


}