package com.example.exerciseday_android.ui.course.custom

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.ExerciseRes
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.databinding.FragmentCustomExerciseBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomExerciseFragment : Fragment() {
    lateinit var binding: FragmentCustomExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomExerciseBinding.inflate(inflater, container, false)

        //이전 fragment에서 넘겨 받기
        val keyword = arguments?.getString("keyword")
        val exerciseName = requireArguments().getString("exerciseName")
        val exerciseIdx = requireArguments().getInt("exerciseIdx")

        initContent(requireContext(), exerciseIdx)

        //fragment 전환을 위한 초기화
        val mainActivity = activity as MainActivity
        val prevFragment = SearchResultFragment()
        val nextFragment = CustomOptionFragment()

        binding.customExerciseBackBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("keyword", keyword)

            prevFragment.arguments = bundle
            mainActivity.replaceFragment(prevFragment)
        }

        binding.customExerciseOptionBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("keyword", keyword)
            bundle.putInt("exerciseIdx", exerciseIdx)
            bundle.putString("exerciseName", exerciseName)

            nextFragment.arguments = bundle
            mainActivity.replaceFragment(nextFragment)
        }

        return binding.root
    }

    private fun initContent(context: Context, exerciseIdx: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.getExercise(exerciseIdx).enqueue(object :
            Callback<ExerciseRes> {
            override fun onFailure(call: Call<ExerciseRes>, t: Throwable) {
                Log.d("server", t.toString())
            }

            override fun onResponse(
                call: Call<ExerciseRes>,
                response: Response<ExerciseRes>
            ) {
                Log.d("success", response.body().toString())
                val url: Uri = Uri.parse("http://3.39.184.186:8080"+response.body()!!.result.exerciseImg)
                Glide.with(context).load(url).into(binding.customExerciseImgIv)
                binding.customExerciseNameTv.text = response.body()!!.result.exerciseName
                binding.customExerciseIntroTv.text = response.body()!!.result.exerciseIntroduce
                binding.customExercisePartTv.text = response.body()!!.result.exercisePart
                binding.customExerciseDetailpartTv.text = response.body()!!.result.exerciseDetailPart
                binding.customExerciseInfoTv.text = response.body()!!.result.exerciseInfo
            }
        })

    }
}