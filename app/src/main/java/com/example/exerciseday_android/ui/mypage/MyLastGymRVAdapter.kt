package com.example.exerciseday_android.ui.mypage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.model.MyLastGym
import com.example.exerciseday_android.databinding.ItemMyLastGymBinding
import com.example.exerciseday_android.databinding.ItemMyLastGymEditBinding
import kotlinx.android.synthetic.main.item_my_last_gym_edit.view.*
import java.util.*
import kotlin.collections.ArrayList

class MyLastGymRVAdapter(private var myLastGymList: ArrayList<MyLastGym>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {

    private lateinit var dragListener: OnStartDragListener

    object GymViewType {
        const val DEFAULT = 0
        const val EDIT = 1
    }

    interface MyItemClickListener {
        fun onItemClick(myLastGym: MyLastGym)
        fun onRemoveGym(myLastGym: MyLastGym)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            GymViewType.EDIT -> {
                return GymEditViewHolder(
                    ItemMyLastGymEditBinding.inflate(
                        LayoutInflater.from(viewGroup.context),
                        viewGroup,
                        false
                    )
                )
            }
            else -> {
                return GymViewHolder(
                    ItemMyLastGymBinding.inflate(
                        LayoutInflater.from(viewGroup.context),
                        viewGroup,
                        false
                    )
                )
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (myLastGymList[position].viewType) {
            GymViewType.EDIT -> {
                (holder as GymEditViewHolder).bind(myLastGymList[position])

                with(holder) {
                    holder.itemView.findViewById<ImageButton>(R.id.item_my_last_gym_move_btn).setOnTouchListener { _, event ->
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            dragListener.onStartDrag(this)
                        }
                        return@setOnTouchListener false
                    }
                }
            }
            else -> {
                (holder as GymViewHolder).bind(myLastGymList[position])
            }
        }
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(myLastGymList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        myLastGymList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = myLastGymList.size

    override fun getItemViewType(position: Int): Int {
        return myLastGymList[position].viewType // 직접 설정한 뷰타입으로 설정되게 만든다.
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: ArrayList<MyLastGym>) {
        this.myLastGymList = items
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setViewType(currentMode: Int) {
        val newMyLastGymList = ArrayList<MyLastGym>()
        for (i in 0 until myLastGymList.size) {
            if (currentMode == 0) {
                // 일반모드
                myLastGymList[i].viewType = GymViewType.DEFAULT
            } else {
                // 편집모드
                myLastGymList[i].viewType = GymViewType.EDIT
            }
            newMyLastGymList.add(myLastGymList[i])
        }

        this.myLastGymList = newMyLastGymList
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun removeGym(position: Int) {
        myLastGymList.removeAt(position)
        notifyDataSetChanged()
    }


    inner class GymViewHolder(val binding: ItemMyLastGymBinding) : ViewHolder(binding.root) {

        private val gymImg = binding.itemMyLastGymIv
        private val gymName = binding.itemMyLastGymNameTv
        private val gymAddress = binding.itemMyLastGymAddressTv
        private val gymUniv = binding.itemMyLastGymUnivTv
        private val gymDistance = binding.itemMyLastGymDistanceTv

        fun bind(item: MyLastGym) {
//        Glide.with(gymImg.context).load("http://3.39.184.186:8080$gymImg").into(gymImg)
            gymImg.setImageResource(R.drawable.temp)  // 임시
            gymName.text = item.gymName
            gymAddress.text = item.gymAddress
            gymUniv.text = item.univ
            gymDistance.text = "에서 ${item.distance}m"
        }
    }

    inner class GymEditViewHolder(val binding: ItemMyLastGymEditBinding) : ViewHolder(binding.root) {

        private val gymImg = binding.itemMyLastGymIv
        private val gymName = binding.itemMyLastGymNameTv
        private val gymAddress = binding.itemMyLastGymAddressTv
        private val gymUniv = binding.itemMyLastGymUnivTv
        private val gymDistance = binding.itemMyLastGymDistanceTv

        fun bind(item: MyLastGym) {
//        Glide.with(gymImg.context).load("http://3.39.184.186:8080$gymImg").into(gymImg)
            gymImg.setImageResource(R.drawable.temp)  // 임시
            gymName.text = item.gymName
            gymAddress.text = item.gymAddress
            gymUniv.text = item.univ
            gymDistance.text = "에서 ${item.distance}m"

            binding.itemMyLastGymRemoveBtn.setOnClickListener {
                removeGym(this.layoutPosition)
            }
        }
    }

}

class ItemTouchHelperCallback(
    private val itemMoveListener: OnItemMoveListener
) : ItemTouchHelper.Callback() {

    interface OnItemMoveListener {
        fun onItemMoved(fromPosition: Int, toPosition: Int)
        fun onItemSwiped(position: Int)
    }


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            // GridLayout 형식인 경우
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        } else {
            // 일반 LinearLayout 형식인 경우
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemMoveListener.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemMoveListener.onItemSwiped(viewHolder.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = false
}