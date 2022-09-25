package com.example.exerciseday_android.ui.gym.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.model.GymTrainer
import com.example.exerciseday_android.data.remote.gym.GymMainResult
import com.example.exerciseday_android.databinding.FragmentGymInfoBinding
import com.example.exerciseday_android.databinding.FragmentGymTrainerBinding
import com.example.exerciseday_android.ui.gym.main.GymMainRVAdapter


class GymTrainerFragment : Fragment() {

    lateinit var binding: FragmentGymTrainerBinding
    private var gymTrainerData = ArrayList<GymTrainer>() // 임시 더미데이터 저장


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymTrainerBinding.inflate(inflater, container, false)

        //트레이너 리사이클러뷰 연결, 더미데이터 넣어놓기

        // 데이터 리스트 생성 더미 데이터 -> // 추후 삭제
        gymTrainerData.apply {
            add(
                GymTrainer(
                    "~",
                    "밍텐 트레이너",
                    arrayListOf("생활 스포츠 지도사(보디빌딩) 2급", "정교사 2급"),
                    arrayListOf("현)작심삼일 PT 대표 트레이너", "2018~2020 애플짐 휘트니스 트레이너")
                )
            )
            add(
                GymTrainer(
                    "~",
                    "치즈 트레이너",
                    arrayListOf("스포츠마사지 1급", "스포프테이핑 1급"),
                    arrayListOf("현)작심삼일 PT 대표 트레이너", "2018~2020 애플짐 휘트니스 트레이너")
                )
            )
        }

        // 트레이너 RecyclerView 어댑터와 데이터 리스트 연결
        val gymTrainerRVAdapter = GymTrainerRVAdapter(gymTrainerData)
        binding.gymTrainerRepresentTrainerRv.adapter = gymTrainerRVAdapter

        binding.gymTrainerIntroRv.adapter =  GymTrainerRVAdapter(gymTrainerData)


        return binding.root
    }

}