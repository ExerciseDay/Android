package com.example.exerciseday_android.ui.gym.detail

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentGymInfoBinding
import com.google.android.material.tabs.TabLayoutMediator


class GymInfoFragment : Fragment() {

    lateinit var binding: FragmentGymInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymInfoBinding.inflate(inflater, container, false)

        binding.gymInfoStarRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.gymInfoStarPointTv.text = rating.toString()
        }


        checkReview()

        // 리뷰 올리기 버튼 클릭 시
        binding.gymInfoReviewUploadBtn.setOnClickListener {
            // 리뷰 등록 API

            // 리뷰 개수 증가
//            binding.gymInfoReviewCountTv.text = "($)"
        }

        // 헬스장 리뷰 - TabLayout, ViewPager2 연결
        val gymReviewFm = childFragmentManager
        val gymReviewLifecycle = viewLifecycleOwner.lifecycle
        val gymReviewVPAdapter =
            GymReviewVPAdapter(gymReviewFm, gymReviewLifecycle)

        gymReviewVPAdapter.addFragment(GymReviewFragment())


        // 리사이ㅡㅋㄹ러뷰를 가져와서,... 나누기 4해서 프래그먼트 에 할당하면서/..
//        if(binding.gymInfoReviewVp.) {
//            gymReviewVPAdapter.addFragment(GymReviewFragment())
//        }

        binding.gymInfoReviewVp.adapter = gymReviewVPAdapter

        TabLayoutMediator(binding.gymInfoReviewTb, binding.gymInfoReviewVp) { tab, position ->
            tab.text = (position + 1).toString()
        }.attach()


        return binding.root
    }


    private fun checkReview() {
        binding.gymInfoReviewContentEt.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                binding.gymInfoReviewUploadBtn.isEnabled = s?.isNotEmpty() == true
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    private inner class GymReviewVPAdapter(
        childFragmentManager: FragmentManager,
        getLifecycle: Lifecycle
    ) :
        FragmentStateAdapter(childFragmentManager, getLifecycle) {

        private val fragmentList: ArrayList<Fragment> = ArrayList()

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
            notifyItemInserted(fragmentList.size - 1)
        }

    }
}