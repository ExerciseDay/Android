package com.example.exerciseday_android.ui.dibs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.Exercise
import com.example.exerciseday_android.data.remote.GetDibsRes
import com.example.exerciseday_android.databinding.FragmentDibsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DibsFragment : Fragment() {
    lateinit var binding: FragmentDibsBinding
    private var dibsList = ArrayList<Exercise>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDibsBinding.inflate(inflater, container, false)

        val dibsListRVAdapter = DibsRVAdapter(dibsList)
        getDibsList(dibsListRVAdapter)

        binding.dibsExerciseListRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.dibsExerciseListRv.adapter = dibsListRVAdapter

        return binding.root
    }

    private fun getDibsList(dibsRVAdapter: DibsRVAdapter) {
        var spf = this.activity?.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)
        var jwt = spf?.getString("jwt", "none").toString()

        spf = this.activity?.getSharedPreferences("userIdx", AppCompatActivity.MODE_PRIVATE)
        var userIdx = spf!!.getInt("userIdx", 0)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.getDibs(jwt, userIdx).enqueue(object :
            Callback<GetDibsRes> {
            override fun onFailure(call: Call<GetDibsRes>, t: Throwable) {
                Log.d("server", t.toString())
            }

            override fun onResponse(call: Call<GetDibsRes>, response: Response<GetDibsRes>) {
                Log.d("server", response.body().toString())

                binding.dibsExerciseNumTv.text = response.body()!!.result.numOfDibs.toString()

                for(i in response.body()!!.result.exercises){
                    dibsRVAdapter.addItem(Exercise(i.exerciseName, i.exerciseIntroduce, i.exercisePart))
                }
            }
        })


    }


}