package com.example.exerciseday_android.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.data.model.MyLastGym
import com.example.exerciseday_android.databinding.FragmentMyLastGymBinding


class MyLastGymFragment : Fragment() {
    private val gymViewModel: GymViewModel by viewModels()

    lateinit var binding: FragmentMyLastGymBinding
    private var myLastGymData = ArrayList<MyLastGym>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyLastGymBinding.inflate(inflater, container, false)


        /*TDL
        -  SharedPreferences 로 최근 본 헬스장 리스트 가져오는 작업
         */

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로가기 눌렀을 때 동작할 코드
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.slide_out, R.anim.fade_in, R.anim.slide_out)
                    .replace(R.id.main_frm, MyPageFragment())
                    .commitAllowingStateLoss()
            }
        })

        // 뒤로 가기
        binding.myLastGymBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out, R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.main_frm, MyPageFragment())
                .commitAllowingStateLoss()
        }


        // 데이터 리스트 생성 더미 데이터
        myLastGymData.apply {
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "광운대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "서울여대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "광운대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "광운대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "광운대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "광운대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
            add(
                MyLastGym(
                    "~",
                    "작심삼일 PT",
                    "어딘가에",
                    "광운대",
                    "100",
                    viewType = MyLastGymRVAdapter.GymViewType.DEFAULT
                )
            )
        }


        // 헬스장 RecyclerView 어댑터와 데이터 리스트 연결
        var myLastGymRVAdapter = MyLastGymRVAdapter(myLastGymData)
        myLastGymRVAdapter.setData(items = myLastGymData)

        myLastGymRVAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            // 데이터의 개수에 따른 View Visibility 설정
            private fun checkIfEmpty() {
                if (binding.myLastGymEmptyLayout != null && binding.myLastGymRv!!.adapter != null) {
                    // 데이터의 개수가 0인지
                    val emptyViewVisible = binding.myLastGymRv!!.adapter!!.itemCount == 0

                    if(emptyViewVisible){
                        binding.myLastGymCountContentTv.text = myLastGymRVAdapter.itemCount.toString() + "개"
                    }else {
                        binding.myLastGymEmptyLayout!!.visibility = View.INVISIBLE
                        binding.myLastGymCountContentTv.visibility = View.VISIBLE
                        binding.myLastGymCountTitleTv.visibility = View.VISIBLE
                        binding.myLastGymCountContentTv.text = myLastGymRVAdapter.itemCount.toString() + "개"
                    }
                }
            }

            // 데이터의 변화에 따라 호출되는 메소드
            override fun onChanged() {
                super.onChanged()
                checkIfEmpty()
            }
        })

        if (myLastGymRVAdapter.itemCount == 0) {
            binding.myLastGymRv.visibility = View.INVISIBLE
            binding.myLastGymCountTitleTv.visibility = View.INVISIBLE
            binding.myLastGymCountContentTv.visibility = View.INVISIBLE
            binding.myLastGymEditBtn.visibility = View.INVISIBLE
            binding.myLastGymEmptyLayout.visibility = View.VISIBLE
            binding.myLastGymCountContentTv.text = myLastGymRVAdapter.itemCount.toString() + "개"
        } else {
            binding.myLastGymEmptyLayout.visibility = View.INVISIBLE
            binding.myLastGymCountTitleTv.visibility = View.VISIBLE
            binding.myLastGymCountContentTv.visibility = View.VISIBLE
            binding.myLastGymEditBtn.visibility = View.VISIBLE
            binding.myLastGymRv.visibility = View.VISIBLE
            binding.myLastGymCountContentTv.text = myLastGymRVAdapter.itemCount.toString() + "개"
        }

        // 편집하기 버튼 클릭 이벤트
        binding.myLastGymEditBtn.setOnClickListener {
            if (gymViewModel.mode.value == 0) {
                // 현재 일반모드 > 편집모드로 변경
                gymViewModel.setMode(mode = 1)
                binding.myLastGymEmptyLayout.visibility = View.GONE
                binding.myLastGymCountTitleTv.visibility = View.VISIBLE
                binding.myLastGymCountContentTv.visibility = View.VISIBLE
                binding.myLastGymEditBtn.visibility = View.VISIBLE
                binding.myLastGymRv.visibility = View.VISIBLE
                binding.myLastGymCountContentTv.text = myLastGymRVAdapter.itemCount.toString() + "개"
            } else {
                // 현재 편집모드 > 일반모드로 변경
                gymViewModel.setMode(mode = 0)
                if (binding.myLastGymRv.adapter!!.itemCount == 0) {
                    binding.myLastGymRv.visibility = View.GONE
                    binding.myLastGymCountTitleTv.visibility = View.GONE
                    binding.myLastGymCountContentTv.visibility = View.GONE
                    binding.myLastGymEditBtn.visibility = View.GONE
                    binding.myLastGymEmptyLayout.visibility = View.VISIBLE
                }
            }
        }

        // 일반/편집모드별 설정
        gymViewModel.mode.observe(viewLifecycleOwner) {
            if (it == 0) {
                // 일반모드
                binding.myLastGymEditBtn.setBackgroundResource(R.drawable.btn_gray800_border13)
                binding.myLastGymEditBtn.setTextColor(resources.getColor(R.color.white, null))
            } else {
                // 편집모드
                binding.myLastGymEditBtn.setBackgroundResource(R.drawable.btn_gray50_border13_gray500)
                binding.myLastGymEditBtn.setTextColor(resources.getColor(R.color.gray_800, null))
            }
            // 모든 데이터의 viewType 바꿔주기
            myLastGymRVAdapter.setViewType(currentMode = it)
        }

        val callback = ItemTouchHelperCallback(myLastGymRVAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.myLastGymRv)
        binding.myLastGymRv.adapter = myLastGymRVAdapter
        myLastGymRVAdapter.startDrag(object : MyLastGymRVAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })


        // 헬스장 RecyclerView 구분선
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.rv_devider, null)!!
        )
        binding.myLastGymRv.addItemDecoration(dividerItemDecoration)



        return binding.root
    }

}

class GymViewModel : ViewModel() {

    private var _mode = MutableLiveData<Int>()
    val mode get() = _mode // 0: 일반, 1: 편집

    init {
        _mode.value = 0 // 기본은 일반모드
    }

    fun setMode(mode: Int) {
        _mode.value = mode
    }
}