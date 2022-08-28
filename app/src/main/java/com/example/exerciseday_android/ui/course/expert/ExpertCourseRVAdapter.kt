package com.example.exerciseday_android.ui.course.expert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.data.remote.course.ExpertListInfo
import com.example.exerciseday_android.databinding.ItemCourseListBinding

class ExpertCourseRVAdapter (private val expertListInfo: ArrayList<ExpertListInfo>) :
    RecyclerView.Adapter<ExpertCourseRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(expertListInfo: ExpertListInfo)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ExpertCourseRVAdapter.ViewHolder {
        val binding: ItemCourseListBinding =
            ItemCourseListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(expertListInfo[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(expertListInfo[position]) }
    }

    override fun getItemCount(): Int = expertListInfo.size

    inner class ViewHolder(val binding: ItemCourseListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(expertListInfo: ExpertListInfo) {
            binding.itemCourseListNameTv.text = expertListInfo.expertName
            binding.itemCourseListDescriptionTv.text = expertListInfo.expertIntroduce
            binding.itemCourseListClockTv.text = (expertListInfo.expertTime / 60).toString() + "분"
        }
    }
}