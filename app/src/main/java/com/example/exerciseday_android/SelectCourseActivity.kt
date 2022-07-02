package com.example.exerciseday_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.ActivitySelectCourseBinding
import com.example.exerciseday_android.databinding.PlusCourseDialogBinding
import com.example.flo.PlusCourseDialogRVAdapter

class SelectCourseActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectCourseBinding
    private var exerciseDatas = ArrayList<Exercise>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

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