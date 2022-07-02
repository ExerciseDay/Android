package com.example.exerciseday_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.ActivityMainBinding
import com.example.exerciseday_android.databinding.ActivitySelectCourseBinding
import com.example.exerciseday_android.databinding.FragmentHomeBinding

class SelectCourseActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectCourseBinding
    private var exerciseDatas = ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}