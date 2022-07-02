package com.example.exerciseday_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.ActivityMainBinding
import com.example.exerciseday_android.databinding.ActivitySelectCourseBinding
import com.example.exerciseday_android.databinding.FragmentHomeBinding

class SelectCourseActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectCourseBinding
    private var exerciseDatas = ArrayList<Exercise>()

    exerciseDatas.apply {
            add(Exercise("벤치프레스(덤벨)", "볼륨감 있는 가슴", "가슴"))
            add(Exercise("인클라인 푸쉬업", "쉬운 밑 가슴", "가슴"))
            add(Exercise("랫 풀다운", "넓고 긴 광배근", "등"))
            add(Exercise("트위스트 슈퍼맨", "코어 및 기립근", "등"))
            add(Exercise("원 암 덤벨 로우", "광배 수축", "등"))
            add(Exercise("바벨 로우", "두께감 있는 등", "등"))
            add(Exercise("스쿼트", "하체 집중", "하체"))
            add(Exercise("와이드 스쿼트", "엉덩이 근육 및 힙업", "하체"))
            add(Exercise("레그 익스텐션", "허벅지 앞쪽", "하체"))
        }

        val customCourseRVAdapter = CustomCourseRVAdapter(exerciseDatas)
        binding.exerciseListRv.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        binding.exerciseListRv.adapter = customCourseRVAdapter
        binding.selectCourseCloseIv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 데이터 리스트 생성 더머 데이터
        exerciseDatas.apply {
            add(Exercise("벤치 프레스", "볼륨감 있는 가슴 만들기", "가슴", R.drawable.img_exercise1))
            add(Exercise("플랭크", "몸의 밸런스 및 코어근력 기르기", "복부", R.drawable.img_exercise2))
            add(Exercise("스쿼트", "탄탄한 허벅지 만들기", "하체", R.drawable.img_exercise3))
        }

        // 어댑터와 데이터 리스트 연결
        val selectCourseRVAdapter = SelectCourseRVAdapter(exerciseDatas)
        binding.selectCourseRecyclerview.adapter = selectCourseRVAdapter
        binding.selectCourseRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        selectCourseRVAdapter.setMyItemClickListener(object :
            SelectCourseRVAdapter.MyItemClickListener {
            override fun onItemClick(exercise: Exercise) {
                // 운동 소개로 액티비티 이동
            }

            override fun onRemoveCourse(position: Int) {
                TODO("Not yet implemented")
            }

        })
    }
}