package com.example.exerciseday_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.databinding.ItemMapGymBinding

class MapGymRVAdapter(private val mapGymList: ArrayList<GymList>) :
    RecyclerView.Adapter<MapGymRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(gymList: GymList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MapGymRVAdapter.ViewHolder {
        val binding: ItemMapGymBinding =
            ItemMapGymBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MapGymRVAdapter.ViewHolder, position: Int) {
        holder.bind(mapGymList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(mapGymList[position]) }
    }

    override fun getItemCount(): Int = mapGymList.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun addGyms(gyms: ArrayList<Gym>) {
//        this.gyms.clear()
//        this.gyms.addAll(gyms)
//
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(val binding: ItemMapGymBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gymList: GymList) {
            binding.itemMapGymImgIv.setImageResource(R.drawable.temp)  // 임시
//            binding.itemMapGymImgIv.setImageURI(Uri.parse(gym.gymImg))
            binding.itemMapGymNameTv.text = gymList.gymName
            binding.itemMapGymAddressTv.text = gymList.gymIntroduce
            binding.itemMapGymDistanceTv.text = gymList.gymDistance.toString()

            // 시설 유무 표시
            if(gymList.gymParking) {
                binding.itemMapGymParkingTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymParkingTv.visibility = View.GONE
            }
            if (gymList.gymSauna) {
                binding.itemMapGymSaunaTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymSaunaTv.visibility = View.GONE
            }
            if(gymList.gymCloths) {
                binding.itemMapGymClothsTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymClothsTv.visibility = View.GONE
            }
            if(gymList.gymShower) {
                binding.itemMapGymShowerTv.visibility = View.VISIBLE
            } else {
                binding.itemMapGymShowerTv.visibility = View.GONE
            }


        }
    }
}