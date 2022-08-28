package com.example.exerciseday_android.ui.course.expert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.data.remote.course.ExpertRoutineInfos
import com.example.exerciseday_android.databinding.ItemExerciseListBinding

class ExpertExerciseRVAdapter(private val expertExerciseList: ArrayList<ExpertRoutineInfos>) :
    RecyclerView.Adapter<ExpertExerciseRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(expertRoutineInfos: ExpertRoutineInfos)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

//    fun addItem(expertRoutineInfos: ExpertRoutineInfos) {
//        expertExerciseList.add(expertRoutineInfos)
//        notifyDataSetChanged()
//    }
//
//    fun removeItem(position: Int) {
//        expertExerciseList.removeAt(position)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemExerciseListBinding =
            ItemExerciseListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(expertExerciseList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(expertExerciseList[position]) }
    }

    override fun getItemCount(): Int = expertExerciseList.size

    inner class ViewHolder(val binding: ItemExerciseListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(expertRoutineInfos: ExpertRoutineInfos) {
            binding.itemExerciseListNameTv.text = expertRoutineInfos.exerciseName
            binding.itemExerciseListDescriptionTv.text = expertRoutineInfos.exerciseIntroduce
            binding.itemExerciseListPositionTv.text = expertRoutineInfos.exercisePart
        }
    }
}