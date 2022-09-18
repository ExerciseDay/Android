package com.example.exerciseday_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.data.model.MyReview
import com.example.exerciseday_android.databinding.ItemMyReviewBinding

class MyReviewRVAdapter(private val myReviewList: ArrayList<MyReview>) :
    RecyclerView.Adapter<MyReviewRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(myReview: MyReview)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemMyReviewBinding =
            ItemMyReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myReviewList[position])
    }

    override fun getItemCount(): Int = myReviewList.size

    inner class ViewHolder(val binding: ItemMyReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myReview: MyReview) {
            binding.itemMyReviewGymNameTv.text = myReview.gymName
            binding.itemMyReviewContentTv.text = myReview.content
            binding.itemMyReviewDateTv.text = myReview.date
            binding.itemMyReviewStarPointTv.text = myReview.starPoint.toString()
        }
    }
}