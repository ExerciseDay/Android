package com.example.exerciseday_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.databinding.ItemCourseSimpleBinding

class CourseExpertListRVAdapter (private val customList: ArrayList<SimpleCourse>): RecyclerView.Adapter<CourseExpertListRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(course: SimpleCourse)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    fun addItem(course: SimpleCourse){
        customList.add(course)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        customList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CourseExpertListRVAdapter.ViewHolder {
        val binding: ItemCourseSimpleBinding =
            ItemCourseSimpleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(customList[position])
        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick(customList[position]) }
    }

    override fun getItemCount(): Int = customList.size

    inner class ViewHolder(val binding: ItemCourseSimpleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(course: SimpleCourse){
            binding.courseTitleTv.text = course.courseName
            binding.courseTimeTv.text = course.courseTime.toString()
            binding.courseCalorieTv.text = course.courseCalory.toString()
        }
    }
}