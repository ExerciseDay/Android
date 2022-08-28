package com.example.exerciseday_android.ui.course.custom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.*
import com.example.exerciseday_android.databinding.FragmentCustomDoneBinding
import com.example.exerciseday_android.ui.temp.CustomExercise
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class CustomDoneFragment : Fragment() {
    lateinit var binding: FragmentCustomDoneBinding
    private var customResult = ArrayList<CustomExercise>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //var index = arguments?.getInt("index")
        val mainActivity = activity as MainActivity
        val homeFragment = HomeFragment()

        binding = FragmentCustomDoneBinding.inflate(inflater, container, false)

        binding.customDoneBackBtn.setOnClickListener {

        }

        binding.customDoneSaveBtn.setOnClickListener {
            Log.d("hi",customResult.toString())

            val title = binding.customDoneCourseTitleEt.text.toString()
            val intro = binding.customDoneCourseIntroEt.text.toString()
            var saveArray = ArrayList<Course2>()

            for (i in customResult) {
                saveArray.add(Course2(i.exerciseIdx, i.rep, i.weight, i.set))
            }

            val userIdx = 1
            val jwt = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NjEzMjg0MDksImV4cCI6MTY2Mjc5OTYzOH0._QOQPrXkRkrKhq1SCB-eMej-tfBcrxjsi3zNAsPFOzU"

            val retrofit = Retrofit.Builder()
                .baseUrl("http://3.39.184.186:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val server: APIS = retrofit.create(APIS::class.java)

            server.saveCourse(jwt, userIdx, SaveCourseBody(title, intro, saveArray)).enqueue(object :
                Callback<SaveCourseRes> {
                override fun onFailure(call: Call<SaveCourseRes>, t: Throwable) {
                    Log.d("server", t.toString())
                }

                override fun onResponse(
                    call: Call<SaveCourseRes>,
                    response: Response<SaveCourseRes>
                ) {
                    Log.d("server", response.body().toString())
                    if (response.body()!!.isSuccess) {
                        val spf = activity?.getSharedPreferences("custom_bag", Context.MODE_PRIVATE)
                        val editor = spf!!.edit()

                        editor.remove("custom_bag")
                        editor.apply()
                        mainActivity.replaceFragment(homeFragment)
                    }
                }
            })
        }

        val customDoneRVAdapter = CustomDoneRVAdapter(customResult)
        getBagList(customDoneRVAdapter)

        binding.customDoneBagRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.customDoneBagRv.adapter = customDoneRVAdapter

        return binding.root
    }

    private fun getBagList(customDoneRVAdapter: CustomDoneRVAdapter) {
        val spf = activity?.getSharedPreferences("custom_bag", Context.MODE_PRIVATE)
        val tempArray = ArrayList<CustomExercise>()
        val gson = GsonBuilder().create()
        val groupListType: Type = object : TypeToken<ArrayList<CustomExercise?>?>() {}.type

        tempArray.addAll(gson.fromJson(spf?.getString("custom_bag", "none"), groupListType))

        for(i in tempArray){
            customDoneRVAdapter.addItem(
                CustomExercise(
                    i.exerciseIdx,
                    i.exerciseName,
                    i.rep,
                    i.weight,
                    i.set
                )
            )
        }
    }

    private fun saveCustomCourse() {
        val spf = activity?.getSharedPreferences("custom_bag", Context.MODE_PRIVATE)
        val tempArray = ArrayList<CustomExercise>()
        val gson = GsonBuilder().create()
        val groupListType: Type = object : TypeToken<ArrayList<CustomExercise?>?>() {}.type

        tempArray.addAll(gson.fromJson(spf?.getString("custom_bag", "none"), groupListType))

        for(i in tempArray){

        }
    }
}