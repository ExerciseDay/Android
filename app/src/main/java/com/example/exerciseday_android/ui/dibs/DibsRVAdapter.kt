package com.example.exerciseday_android.ui.dibs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.Exercise
import com.example.exerciseday_android.databinding.ItemExerciseListBinding

class DibsRVAdapter (private val dibsList: ArrayList<Exercise>): RecyclerView.Adapter<DibsRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(exercise: Exercise)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    fun addItem(exercise: Exercise){
        dibsList.add(exercise)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        dibsList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemExerciseListBinding =
            ItemExerciseListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dibsList[position])
        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick(dibsList[position]) }
    }

    override fun getItemCount(): Int = dibsList.size

    inner class ViewHolder(val binding: ItemExerciseListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(exercise: Exercise){
            binding.itemExerciseListNameTv.text = exercise.title
            binding.itemExerciseListDescriptionTv.text = exercise.description
            binding.itemExerciseListPositionTv.text = exercise.position
        }
    }
}