package com.example.exerciseday_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.databinding.ItemSelectCourseBinding

class SelectCourseRVAdapter(private val exerciseList: ArrayList<Exercise>) :
    RecyclerView.Adapter<SelectCourseRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(exercise: Exercise)
        fun onRemoveCourse(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    fun addItem(exercise: Exercise) {
        exerciseList.add(exercise)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        exerciseList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SelectCourseRVAdapter.ViewHolder {
        val binding: ItemSelectCourseBinding =
            ItemSelectCourseBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectCourseRVAdapter.ViewHolder, position: Int) {
        holder.bind(exerciseList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(exerciseList[position]) }
//        holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) }
    }

    override fun getItemCount(): Int = exerciseList.size

    inner class ViewHolder(val binding: ItemSelectCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.itemExerciseTitleTv.text = exercise.title
            binding.itemExerciseIntroTv.text = exercise.intro
            binding.itemExerciseType.text = exercise.type
            binding.itemExerciseCoverImgIv.setImageResource(exercise.coverImg!!)
        }
    }
}