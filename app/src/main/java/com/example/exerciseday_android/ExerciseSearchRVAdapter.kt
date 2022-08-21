package com.example.exerciseday_android

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.databinding.ItemExerciseBinding
import com.example.exerciseday_android.databinding.ItemSelectCourseBinding
import okhttp3.internal.notify
import com.example.exerciseday_android.ExerciseSearchRVAdapter.MyItemClickListener as MyItemClickListener1

class ExerciseSearchRVAdapter(private val exerciseSearchList: ArrayList<ExerciseSearch>) :
    RecyclerView.Adapter<ExerciseSearchRVAdapter.ViewHolder>() {
    interface MyItemClickListener {
        fun onItemClick(exercise: ExerciseSearch)
    }

    private lateinit var mItemClickListener: MyItemClickListener1

    fun setMyITemClickListener(itemClickListener: MyItemClickListener1) {
        mItemClickListener = itemClickListener
    }

    fun addItem(exercise: ExerciseSearch) {
        exerciseSearchList.add(exercise)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        exerciseSearchList.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        exerciseSearchList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            ExerciseSearchRVAdapter.ViewHolder {
        val binding: ItemExerciseBinding =
            ItemExerciseBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exerciseSearchList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(exerciseSearchList[position]) }

    }

    override fun getItemCount(): Int = exerciseSearchList.size

    inner class ViewHolder(val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: ExerciseSearch) {
            binding.exerciseNameTv.text = exercise.name
            binding.exerciseIntroduceTv.text = exercise.description
            binding.exerciseTypeTv.text = exercise.part
        }
    }
}
