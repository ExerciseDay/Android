package com.example.exerciseday_android

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.exerciseday_android.databinding.ItemUserCourseBinding

class HomeCourseRVAdapter(private val courseList: ArrayList<UserCourse>): RecyclerView.Adapter<HomeCourseRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(course: UserCourse)
        fun onRemoveAlbum(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    fun addItem(course: UserCourse){
        courseList.add(course)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        courseList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeCourseRVAdapter.ViewHolder {
        val binding: ItemUserCourseBinding =
            ItemUserCourseBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseList[position])
        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick(courseList[position]) }
    }

    override fun getItemCount(): Int = courseList.size

    inner class ViewHolder(val binding: ItemUserCourseBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(userCourse: UserCourse){
            binding.courseTitleTv.text = userCourse.title
            binding.courseTimeTv.text = userCourse.time.toString()
            binding.courseCalorieTv.text = userCourse.calorie.toString()
        }
    }


}