package com.example.exerciseday_android.ui.gym

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.databinding.ItemGymSearchResultBinding

class GymSearchRVAdapter (private val gymSearchList: ArrayList<GymSearchResult>): RecyclerView.Adapter<GymSearchRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(gymSearchResult: GymSearchResult)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    fun addItem(gymSearchResult: GymSearchResult){
        gymSearchList.add(gymSearchResult)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        gymSearchList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGymSearchResultBinding =
            ItemGymSearchResultBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(gymSearchList[position])
    }

    override fun getItemCount(): Int = gymSearchList.size

    inner class ViewHolder(val binding: ItemGymSearchResultBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(gymSearchResult: GymSearchResult){
            binding.gymResultNameTv.text = gymSearchResult.name
            binding.gymResultAddressTv.text = gymSearchResult.address
            binding.gymResultDistanceTv.text = gymSearchResult.distance.toString()
        }
    }
}