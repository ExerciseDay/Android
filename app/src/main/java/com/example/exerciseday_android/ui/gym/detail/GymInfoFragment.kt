package com.example.exerciseday_android.ui.gym.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseday_android.databinding.FragmentGymInfoBinding
import com.google.android.material.tabs.TabLayoutMediator


class GymInfoFragment : Fragment() {

    lateinit var binding: FragmentGymInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymInfoBinding.inflate(inflater, container, false)

        binding.gymInfoStarRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.gymInfoStarPointTv.text = rating.toString()
        }

        val gymReviewFm = childFragmentManager
        val gymReviewLifecycle = viewLifecycleOwner.lifecycle
        val gymReviewVPAdapter =
            GymReviewVPAdapter(gymReviewFm, gymReviewLifecycle)
        binding.gymInfoReviewVp.adapter = gymReviewVPAdapter

        TabLayoutMediator(binding.gymInfoReviewTb, binding.gymInfoReviewVp) { tab, position ->
            tab.text = (position + 1).toString()
        }.attach()

        checkReview()

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