package com.example.exerciseday_android.ui.expert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.databinding.ItemBodyPartBtnBinding

class BodyDetailPartRVAdapter(private val bodyDetailPartList: Array<String>) :
    RecyclerView.Adapter<BodyDetailPartRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(bodyDetailPartList: String)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemBodyPartBtnBinding =
            ItemBodyPartBtnBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bodyDetailPartList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(bodyDetailPartList[position]) }
    }

    override fun getItemCount(): Int = bodyDetailPartList.size

    inner class ViewHolder(val binding: ItemBodyPartBtnBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bodyDetailPartList: String) {
            binding.itemBodyPartNameTv.text = bodyDetailPartList
        }
    }
}