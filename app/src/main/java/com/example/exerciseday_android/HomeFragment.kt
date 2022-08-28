package com.example.exerciseday_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.FragmentHomeBinding
import com.example.exerciseday_android.ui.course.CourseCustomListRVAdapter
import com.example.exerciseday_android.ui.course.CourseExpertListRVAdapter
import com.example.exerciseday_android.ui.course.CourseMakeFragment
import com.example.exerciseday_android.ui.course.SimpleCourse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var customList = ArrayList<SimpleCourse>()
    private var expertList = ArrayList<SimpleCourse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

//        binding.searchBtn.setOnClickListener {
//            val intent = Intent(context, GymSearchActivity::class.java)
//            startActivity(intent)
//        }

        //RV
        val courseCustomListRVAdapter = CourseCustomListRVAdapter(customList)
        val courseExpertListRVAdapter = CourseExpertListRVAdapter(expertList)
        getCourseList(courseCustomListRVAdapter, courseExpertListRVAdapter)

        binding.customRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.customRecycleView.adapter = courseCustomListRVAdapter

        binding.expertRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.expertRecycleView.adapter = courseExpertListRVAdapter



        binding.makeCourseBtn.setOnClickListener {
            val courseMakeFragment = CourseMakeFragment()
            mainActivity.replaceFragment(courseMakeFragment)
        }

        return binding.root
    }

//    private fun changeSelectCourseActivity(course: Course) {
//        val intent = Intent(context, SelectCourseActivity::class.java)
//        startActivity(intent)
//    }
    private fun getCourseList(courseCustomListRVAdapter: CourseCustomListRVAdapter, courseExpertListRVAdapter: CourseExpertListRVAdapter) {
        val userIdx = 1
        val jwt = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NjEzMjg0MDksImV4cCI6MTY2Mjc5OTYzOH0._QOQPrXkRkrKhq1SCB-eMej-tfBcrxjsi3zNAsPFOzU"

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.getCourse(jwt ,userIdx).enqueue(object :
            Callback<GetCourseRes> {
            override fun onFailure(call: Call<GetCourseRes>, t: Throwable) {
                Log.d("server", t.toString())
            }

            override fun onResponse(
                call: Call<GetCourseRes>,
                response: Response<GetCourseRes>
            ) {
                Log.d("server", response.body().toString())
                if (response.body()!!.isSuccess){
                    val getCustomList = response.body()!!.result.customList
                    val getExpertList = response.body()!!.result.expertList
                    Log.d("custom", getCustomList.toString())
                    Log.d("expert", getExpertList.toString())

                    for(i in getCustomList){
                        courseCustomListRVAdapter.addItem(
                            SimpleCourse(i.customIdx, i.customName, i.customTime, i.customCalory)
                        )
                    }

                    for(j in getExpertList){
                        courseExpertListRVAdapter.addItem(
                            SimpleCourse(j.expertIdx, j.expertName, j.expertTime, j.expertCalory)
                        )
                    }
                }
            }
        })
    }

}