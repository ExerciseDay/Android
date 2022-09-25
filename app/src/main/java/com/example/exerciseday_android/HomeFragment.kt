package com.example.exerciseday_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.FragmentHomeBinding
import com.example.exerciseday_android.ui.course.CourseCustomListRVAdapter
import com.example.exerciseday_android.ui.course.CourseExpertListRVAdapter
import com.example.exerciseday_android.ui.course.CourseMakeFragment
import com.example.exerciseday_android.ui.course.SimpleCourse
import com.example.exerciseday_android.ui.dibs.DibsFragment
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

        binding.dipsListBtn.setOnClickListener {
            val dibsFragment = DibsFragment()
            mainActivity.replaceFragment(dibsFragment)
        }

        return binding.root
    }

    private fun getCourseList(courseCustomListRVAdapter: CourseCustomListRVAdapter, courseExpertListRVAdapter: CourseExpertListRVAdapter) {
        var spf = this.activity?.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)
        var jwt = spf!!.getString("jwt", "none").toString()

        spf = this.activity?.getSharedPreferences("userIdx", AppCompatActivity.MODE_PRIVATE)
        var userIdx = spf!!.getInt("userIdx", 0)

        Log.d("jwt", jwt)

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