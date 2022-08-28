package com.example.exerciseday_android.ui.course.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.ui.temp.CustomExercise
import com.example.exerciseday_android.databinding.ItemCustomDoneBinding

class CustomDoneRVAdapter(private val customBagList: ArrayList<CustomExercise>) :
    RecyclerView.Adapter<CustomDoneRVAdapter.ViewHolder>() {
    interface MyItemClickListener {
        fun onItemClick(exercise: CustomExercise)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyITemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    fun addItem(exercise: CustomExercise) {
        customBagList.add(exercise)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        customBagList.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        customBagList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            ViewHolder {
        val binding: ItemCustomDoneBinding =
            ItemCustomDoneBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(customBagList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(customBagList[position]) }

    }

    override fun getItemCount(): Int = customBagList.size

    inner class ViewHolder(val binding: ItemCustomDoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: CustomExercise) {
            binding.exerciseNameTv.text = exercise.exerciseName
            binding.exerciseRepTv.text = exercise.rep.toString()
            binding.exerciseWeightTv.text = exercise.weight.toString()
            binding.exerciseSetTv.text = exercise.set.toString()
        }
    }
}