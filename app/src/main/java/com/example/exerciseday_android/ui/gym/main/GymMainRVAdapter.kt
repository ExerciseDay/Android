package com.example.exerciseday_android.ui.gym.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.data.remote.gym.GymMainResult
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ItemMapGymBinding

class GymMainRVAdapter(private val mapGymMainResult: ArrayList<GymMainResult>) :
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
        val binding: ItemMapGymBinding =
            ItemMapGymBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mapGymMainResult[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(mapGymMainResult[position]) }
    }

    override fun getItemCount(): Int = mapGymMainResult.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun addGyms(gyms: ArrayList<Gym>) {
//        this.gyms.clear()
//        this.gyms.addAll(gyms)
//
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(val binding: ItemMapGymBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gymMainResult: GymMainResult) {
            binding.itemMapGymImgIv.setImageResource(R.drawable.temp)  // 임시
//            binding.itemMapGymImgIv.setImageURI(Uri.parse(gym.gymImg))
            binding.itemMapGymNameTv.text = gymMainResult.gymName
            binding.itemMapGymAddressTv.text = gymMainResult.gymIntroduce
            binding.itemMapGymDistanceTv.text = gymMainResult.gymDistance.toString()

            // 시설 유무 표시
            if(gymMainResult.gymParking) {
                binding.itemMapGymParkingTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymParkingTv.visibility = View.GONE
            }
            if (gymMainResult.gymSauna) {
                binding.itemMapGymSaunaTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymSaunaTv.visibility = View.GONE
            }
            if(gymMainResult.gymCloths) {
                binding.itemMapGymClothsTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymClothsTv.visibility = View.GONE
            }
            if(gymMainResult.gymShower) {
                binding.itemMapGymShowerTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymShowerTv.visibility = View.GONE
            }


        }
    }
}