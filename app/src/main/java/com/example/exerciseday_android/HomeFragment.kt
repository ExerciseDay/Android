package com.example.exerciseday_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.FragmentHomeBinding
import com.example.exerciseday_android.databinding.PlusCourseDialogBinding
import com.example.flo.PlusCourseDialogRVAdapter
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var courseDatas = ArrayList<Course>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 데이터 리스트 생성 더머 데이터
        courseDatas.apply {
            add(Course("커스텀 운동 코스"))
            add(Course("커스텀 운동 코스"))
            add(Course("커스텀 운동 코스"))
        }

        binding.fabMain.setOnClickListener {
            var builder = AlertDialog.Builder(requireContext())
            val dialogBinding: PlusCourseDialogBinding = PlusCourseDialogBinding.inflate(layoutInflater)
            builder.setView(dialogBinding.root)

            // 어댑터와 데이터 리스트 연결
            val plusCourseDialogRVAdapter = PlusCourseDialogRVAdapter(courseDatas)
            dialogBinding.plusCourseRecyclerView.adapter = plusCourseDialogRVAdapter
            dialogBinding.plusCourseRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


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