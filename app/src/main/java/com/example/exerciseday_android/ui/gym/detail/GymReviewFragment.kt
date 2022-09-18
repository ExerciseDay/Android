package com.example.exerciseday_android.ui.gym.detail

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
import com.example.exerciseday_android.databinding.FragmentGymReviewBinding


class GymReviewFragment : Fragment() {

    lateinit var binding: FragmentGymReviewBinding
    private var gymReviewData = ArrayList<GymReview>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymReviewBinding.inflate(inflater, container, false)


        // 데이터 리스트 생성 더미 데이터
        gymReviewData.apply {
            add(
                GymReview(
                    "김하루",
                    "PT 받고 살 많이 빠진거같아요 추천!",
                    3.0
                )
            )
            add(
                GymReview(
                    "멸치팡팡",
                    "트레이너님이 친절하고 좋아요~!",
                    4.0
                )
            )
            add(
                GymReview(
                    "멸치팡팡",
                    "트레이너님이 친절하고 좋아요~!",
                    4.0
                )
            )
            add(
                GymReview(
                    "멸치팡팡",
                    "트레이너님이 친절하고 좋아요~!",
                    4.0
                )
            )
            add(
                GymReview(
                    "멸치팡팡",
                    "트레이너님이 친절하고 좋아요~!",
                    4.0
                )
            )
        }

        // 리뷰 RecyclerView 어댑터와 데이터 리스트 연결
        val gymReviewRVAdapter = GymReviewRVAdapter(gymReviewData)
        binding.gymReviewRv.adapter = gymReviewRVAdapter

        // 리뷰 RecyclerView 구분선
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.rv_devider, null)!!
        )
        binding.gymReviewRv.addItemDecoration(dividerItemDecoration)



        return binding.root
    }


}