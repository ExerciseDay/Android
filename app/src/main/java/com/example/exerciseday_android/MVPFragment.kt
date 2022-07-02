package com.example.exerciseday_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.FragmentMvpBinding
import com.example.exerciseday_android.databinding.PlusCourseDialogBinding
import com.example.flo.PlusCourseDialogRVAdapter
import kotlin.collections.ArrayList

class MVPFragment : Fragment() {
    lateinit var binding: FragmentMvpBinding
    private var courseDatas = ArrayList<Course>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMvpBinding.inflate(inflater, container, false)

        // 데이터 리스트 생성 더머 데이터
        courseDatas.apply {
            add(Course("커스텀 운동 코스"))
            add(Course("커스텀 운동 코스"))
            add(Course("커스텀 운동 코스"))
        }

        binding.fabMain.setOnClickListener {
            var builder = AlertDialog.Builder(requireContext())
            val bind: PlusCourseDialogBinding = PlusCourseDialogBinding.inflate(layoutInflater)
            builder.setView(bind.root)
            bind.plusCourseRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


            // 어댑터와 데이터 리스트 연결
            val plusCourseDialogRVAdapter = PlusCourseDialogRVAdapter(courseDatas)
            bind.plusCourseRecyclerView.adapter = plusCourseDialogRVAdapter
            bind.plusCourseRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


            plusCourseDialogRVAdapter.setMyItemClickListener(object :
                PlusCourseDialogRVAdapter.MyItemClickListener {
                override fun onItemClick(course: Course) {
                    // 운동 선택으로 액티비티 이동
                    changeSelectCourseActivity(course)
                }

                override fun onRemoveCourse(position: Int) {
                    TODO("Not yet implemented")
                }

            })
            builder.show()
        }
        return binding.root
    }

    private fun changeSelectCourseActivity(course: Course) {
        val intent = Intent(context, SelectCourseActivity::class.java)
        startActivity(intent)
    }
}