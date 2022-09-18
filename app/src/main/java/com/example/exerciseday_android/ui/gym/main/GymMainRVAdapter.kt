package com.example.exerciseday_android.ui.gym.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exerciseday_android.data.remote.gym.GymMainResult
import com.example.exerciseday_android.databinding.ItemGymMainBinding

class GymMainRVAdapter(private val gymMainResult: ArrayList<GymMainResult>) :
    RecyclerView.Adapter<GymMainRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(gymMainResult: GymMainResult)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemGymMainBinding =
            ItemGymMainBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gymMainResult[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(gymMainResult[position]) }
    }

    override fun getItemCount(): Int = gymMainResult.size

    inner class ViewHolder(val binding: ItemGymMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gymMainResult: GymMainResult) {
            Glide.with(binding.root).load("http://3.39.184.186:8080" + gymMainResult.gymImg).into(binding.itemMapGymImgIv)

//            binding.itemMapGymImgIv.setImageResource(R.drawable.temp)  // 임시
            binding.itemMapGymNameTv.text = gymMainResult.gymName
            binding.itemMapGymAddressTv.text = gymMainResult.gymIntroduce
            binding.itemMapGymDistanceTv.text = gymMainResult.gymDistance.toString()

            // 시설 유무 표시
            if (gymMainResult.gymParking) {
                binding.itemMapGymParkingTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymParkingTv.visibility = View.GONE
            }
            if (gymMainResult.gymSauna) {
                binding.itemMapGymSaunaTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymSaunaTv.visibility = View.GONE
            }
            if (gymMainResult.gymCloths) {
                binding.itemMapGymClothsTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymClothsTv.visibility = View.GONE
            }
            if (gymMainResult.gymShower) {
                binding.itemMapGymShowerTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymShowerTv.visibility = View.GONE
            }


        }
    }
}