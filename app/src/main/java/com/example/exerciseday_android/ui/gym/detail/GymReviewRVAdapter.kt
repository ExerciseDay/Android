package com.example.exerciseday_android.ui.gym.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.data.model.GymReview
import com.example.exerciseday_android.databinding.ItemGymReviewBinding

class GymReviewRVAdapter(private val gymReviewList: ArrayList<GymReview>) :
    RecyclerView.Adapter<GymReviewRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(gymReview: GymReview)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemGymReviewBinding =
            ItemGymReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gymReviewList[position])
    }

    override fun getItemCount(): Int = gymReviewList.size

    inner class ViewHolder(val binding: ItemGymReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gymReview: GymReview) {
            binding.itemGymReviewNicknameTv.text = gymReview.nickname
            binding.itemGymReviewContentTv.text = gymReview.content
            binding.itemGymReviewStarPointTv.text = gymReview.starPoint.toString()
        }
    }
}