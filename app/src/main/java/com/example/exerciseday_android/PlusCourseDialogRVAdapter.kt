package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.Course
import com.example.exerciseday_android.databinding.ItemPlusCourseDialogBinding

class PlusCourseDialogRVAdapter(private val courseList: ArrayList<Course>) :
    RecyclerView.Adapter<PlusCourseDialogRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(course: Course)
        fun onRemoveCourse(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    fun addItem(course: Course) {
        courseList.add(course)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        courseList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): PlusCourseDialogRVAdapter.ViewHolder {
        val binding: ItemPlusCourseDialogBinding =
            ItemPlusCourseDialogBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlusCourseDialogRVAdapter.ViewHolder, position: Int) {
        holder.bind(courseList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(courseList[position]) }
//        holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) }
    }

    override fun getItemCount(): Int = courseList.size

    inner class ViewHolder(val binding: ItemPlusCourseDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.itemPlusCourseTitleTv.text = course.title
        }
    }
}