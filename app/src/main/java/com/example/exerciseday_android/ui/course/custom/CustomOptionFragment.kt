package com.example.exerciseday_android.ui.course.custom

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.ui.temp.CustomExercise
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.databinding.FragmentCustomOptionBinding
import com.example.exerciseday_android.ui.ChangeDialog
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CustomOptionFragment : Fragment() {
    lateinit var binding: FragmentCustomOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomOptionBinding.inflate(inflater, container, false)
        val keyword = requireArguments().getString("keyword")
        val exerciseIdx = requireArguments().getInt("exerciseIdx")
        val exerciseName = requireArguments().getString("exerciseName")

        val mainActivity = activity as MainActivity
        val prevFragment = CustomExerciseFragment()
        val nextFragment = SearchResultFragment()

        binding.customOptionBackBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("keyword", keyword)
            bundle.putInt("exerciseIdx", exerciseIdx)
            bundle.putString("exerciseName", exerciseName)

            prevFragment.arguments = bundle
            mainActivity.replaceFragment(prevFragment)
        }

        binding.customOptionGetBtn.setOnClickListener {
            val rep = binding.customOptionRepEt.text.toString()
            val weight = binding.customOptionWeightEt.text.toString()
            val set = binding.customOptionSetEt.text.toString()

            val spf = activity?.getSharedPreferences("custom_bag", MODE_PRIVATE)
            val editor = spf!!.edit()
            val gson = GsonBuilder().create()
            val data = CustomExercise(exerciseIdx, exerciseName!!, rep.toInt(), weight.toInt(), set.toInt())
            val tempArray = ArrayList<CustomExercise>()
            val groupListType: Type = object: TypeToken<ArrayList<CustomExercise?>?>() {}.type
//            tempArray.add(data)
//            val strList = gson.toJson(tempArray, groupListType)
//            editor.putString("custom_bag", strList)
//            editor.apply()
//            Log.d("dd", spf.getString("custom_bag", "none").toString())

            val prev =spf.getString("custom_bag", "none") // json list 가져오기

            if(prev!="none"){ //데이터가 비어있지 않다면?
                if(prev!="[]" || prev!="")
                    tempArray.addAll(gson.fromJson(prev,groupListType))
                tempArray.add(data)
                val strList = gson.toJson(tempArray,groupListType)
                editor.putString("custom_bag", strList)
            }else{
                tempArray.add(data)
                val strList = gson.toJson(tempArray,groupListType)
                editor.putString("custom_bag", strList)
            }
            editor.apply()

            Log.d("dd", spf.getString("custom_bag", "none").toString())

            val dialog = ChangeDialog(mainActivity)
            dialog.show("추가되셨습니다!")
            dialog.btnClickListener {
                val bundle = Bundle()
                bundle.putString("keyword", keyword)

                nextFragment.arguments = bundle
                mainActivity.replaceFragment(nextFragment)
            }
        }

        return binding.root
    }
}