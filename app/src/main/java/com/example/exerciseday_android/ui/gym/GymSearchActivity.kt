package com.example.exerciseday_android.ui.gym

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.HomeCourseRVAdapter
import com.example.exerciseday_android.databinding.ActivityGymSearchBinding

class GymSearchActivity : AppCompatActivity() {
    lateinit var binding : ActivityGymSearchBinding
    private var gymData = ArrayList<GymSearchResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGymSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gymData.apply {
            add(GymSearchResult("작심삼일", "서울 노원구 광운로 29 3층", 383))
            add(GymSearchResult("작심삼일 헬스장", "서울 노원구 광운로 29 3층", 383))
            add(GymSearchResult("작심삼일 끝 휘트니스", "서울 노원구 광운로 29 3층", 383))
        }

        binding.temp.visibility = View.GONE

        binding.gymSearchCloseIb.setOnClickListener {
            finish()
        }

        val gymSearchRVAdapter = GymSearchRVAdapter(gymData)
        binding.gymSearchRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.gymSearchRv.adapter = gymSearchRVAdapter

        binding.gymSearchEt.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    binding.gymSearchDefaultL.visibility = View.GONE
                    binding.temp.visibility = View.VISIBLE
                } else {
                    binding.gymSearchDefaultL.visibility = View.VISIBLE
                    binding.temp.visibility = View.VISIBLE
                }
            }
    }
}