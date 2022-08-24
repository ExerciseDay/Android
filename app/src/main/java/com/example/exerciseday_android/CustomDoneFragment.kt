package com.example.exerciseday_android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.FragmentCustomDoneBinding
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
        binding = FragmentCustomDoneBinding.inflate(inflater, container, false)

        binding.customDoneBackBtn.setOnClickListener {

        }

        binding.customDoneSaveBtn.setOnClickListener {
            Log.d("hi",customResult.toString())
            val spf = activity?.getSharedPreferences("custom_bag", Context.MODE_PRIVATE)
            val editor = spf!!.edit()
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

    }
}