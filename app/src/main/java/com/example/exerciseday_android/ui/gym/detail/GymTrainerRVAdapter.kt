package com.example.exerciseday_android.ui.gym.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.data.model.GymTrainer
import com.example.exerciseday_android.databinding.ItemGymTrainerBinding

class GymTrainerRVAdapter(private val gymTrainerList: ArrayList<GymTrainer>) :
    RecyclerView.Adapter<GymTrainerRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(gymTrainer: GymTrainer)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyITemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemGymTrainerBinding =
            ItemGymTrainerBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gymTrainerList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(gymTrainerList[position]) }
    }

    override fun getItemCount(): Int = gymTrainerList.size

    inner class ViewHolder(val binding: ItemGymTrainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gymTrainer: GymTrainer) {
//            binding.itemGymTrainerTrainerIv.
            binding.itemGymTrainerTrainerNameTv.text = gymTrainer.gymTrainerName
            binding.itemGymTrainerTrainerAwardContentTv.text =
                gymTrainer.gymTrainerAward.toString().replace("[", "").replace("]", "").replace(", ","\n")
            binding.itemGymTrainerTrainerCareerContentTv.text =
                gymTrainer.gymTrainerCareer.toString().replace("[", "").replace("]", "").replace(", ","\n")
        }
    }
}