package com.example.exerciseday_android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseday_android.databinding.FragmentSearchResultBinding
import com.example.exerciseday_android.ui.gym.GymSearchRVAdapter
import com.example.exerciseday_android.ui.gym.GymSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchResultFragment : Fragment() {
    lateinit var binding: FragmentSearchResultBinding
    private var searchResult = ArrayList<ExerciseSearch>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var keyword = arguments?.getString("keyword")
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        binding.searchResultSearchEt.setText(keyword.toString())
        binding.searchResultSummary2Tv.text = keyword.toString()

        val exerciseSearchRVAdapter = ExerciseSearchRVAdapter(searchResult)
        searchExercise(keyword.toString(), exerciseSearchRVAdapter)

        binding.searchResultRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchResultRv.adapter = exerciseSearchRVAdapter

        binding.searchResultSearchEt.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    binding.searchResultSummary2Tv.text = binding.searchResultSearchEt.text.toString()

                    exerciseSearchRVAdapter.clear()

                    searchExercise(binding.searchResultSearchEt.text.toString(), exerciseSearchRVAdapter)
                }
                return false
            }
        })


        return binding.root
    }

    private fun searchExercise(keyword: String, exerciseSearchRVAdapter: ExerciseSearchRVAdapter) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.searchExercise(keyword).enqueue(object :
            Callback<SearchExerciseRes> {
            override fun onFailure(call: Call<SearchExerciseRes>, t: Throwable) {
                Log.d("server", t.toString())

            }

            override fun onResponse(call: Call<SearchExerciseRes>, response: Response<SearchExerciseRes>) {
                var list = response.body()!!.result.exercises

                if(response.body()!!.isSuccess){
                    if (list.size > 4){
                        for(i in 1..4){
                            exerciseSearchRVAdapter.addItem(ExerciseSearch(list[i].exerciseName, list[i].exercisePart, list[i].exerciseIntroduce))
                        }
                    } else {
                        for(i in list){
                            exerciseSearchRVAdapter.addItem(ExerciseSearch(i.exerciseName, i.exercisePart, i.exerciseIntroduce))
                        }
                    }
                }

            }
        })
    }
}