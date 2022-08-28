package com.example.exerciseday_android.ui.course.custom

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.exerciseday_android.Exercise
import com.example.exerciseday_android.databinding.ItemSelectCourseBinding


class CustomCourseRVAdapter(private val exerciseList: ArrayList<Exercise>): RecyclerView.Adapter<CustomCourseRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(exercise: Exercise)
        fun onRemoveAlbum(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    fun addItem(exercise: Exercise){
        exerciseList.add(exercise)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        exerciseList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSelectCourseBinding =
            ItemSelectCourseBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exerciseList[position])
        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick(exerciseList[position]) }
    }

    override fun getItemCount(): Int = exerciseList.size

    inner class ViewHolder(val binding: ItemSelectCourseBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(exercise: Exercise){
            binding.customExerciseTitleTv.text = exercise.title
            binding.customExerciseDescriptionTv.text = exercise.description
            binding.customExercisePositionTv.text = exercise.position
        }
    }
}