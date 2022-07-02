package com.example.exerciseday_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.ActivityMainBinding
import com.example.exerciseday_android.databinding.PlusCourseDialogBinding
import com.example.flo.PlusCourseDialogRVAdapter
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var courseDatas = ArrayList<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.fabMain.setOnClickListener {
//
//            var builder = AlertDialog.Builder(this)
//
//            val bind: PlusCourseDialogBinding = PlusCourseDialogBinding.inflate(layoutInflater)
//            builder.setView(bind.root)
//            bind.plusCourseRecyclerView.layoutManager =
//                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//            // 데이터 리스트 생성 더머 데이터
//            courseDatas.apply {
//                add(Course("커스텀 운동 코스"))
//                add(Course("커스텀 운동 코스"))
//                add(Course("커스텀 운동 코스"))
//            }
//
//            // 어댑터와 데이터 리스트 연결
//            val plusCourseDialogRVAdapter = PlusCourseDialogRVAdapter(courseDatas)
//
//            bind.plusCourseRecyclerView.adapter = plusCourseDialogRVAdapter
//            bind.plusCourseRecyclerView.layoutManager =
//                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//            plusCourseDialogRVAdapter.setMyItemClickListener(object :
//                PlusCourseDialogRVAdapter.MyItemClickListener {
//                override fun onItemClick(course: Course) {
//                    // 운동 선택으로 액티비티 이동
//                    changeSelectCourseActivity(course)
//                }
//
//                override fun onRemoveCourse(position: Int) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//            builder.show()
//        }
//    }
//
//    private fun changeSelectCourseActivity(course: Course) {
//        val intent = Intent(this, SelectCourseActivity::class.java)
//        startActivity(intent)
//    }

        initBottomNavigation()
    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavV.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.recordFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, RecordFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.communityFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, CommunityFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.mapFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MapFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.settingFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SettingFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}