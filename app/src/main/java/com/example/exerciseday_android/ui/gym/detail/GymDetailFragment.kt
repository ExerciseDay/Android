package com.example.exerciseday_android.ui.gym.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.MapFragment
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentGymDetailBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs


class GymDetailFragment : Fragment() {

    lateinit var binding: FragmentGymDetailBinding
    private val gymDetailTab = arrayListOf("헬스장 소개", "트레이너")
    private var currentImgIndex = 0
    private var gymImageViews = ArrayList<ImageView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymDetailBinding.inflate(inflater, container, false)


        // 뒤로 가기
        binding.gymDetailBackBtn.setOnClickListener {
            // 헬스장 메인 페이지(MapFragment)로 이동
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .replace(R.id.main_frm, MapFragment())
                .commitAllowingStateLoss()
        }


        initView()


        // 헬스장 이미지 클릭 시
        binding.gymDetailGymImgVp.setOnClickListener {
            val intent = Intent(activity, GymImgListActivity::class.java)
            startActivity(intent)
        }

        // [헬스장 세부 페이지] 헬스장 소개, 트레이너 - TabLayout, ViewPager2 연결
        val gymDetailFm = childFragmentManager
        val gymDetailLifecycle = viewLifecycleOwner.lifecycle
        val gymDetailVPAdapter =
            GymDetailVPAdapter(gymDetailFm, gymDetailLifecycle)
        binding.gymDetailGymVp.adapter = gymDetailVPAdapter

        TabLayoutMediator(binding.gymDetailGymTb, binding.gymDetailGymVp) { tab, position ->
            tab.text = gymDetailTab[position]
        }.attach()


        // Tab 커스텀
        val selectedTab: TabLayout.Tab? = binding.gymDetailGymTb.getTabAt(0)
        setTabTypeface(
            selectedTab!!,
            ResourcesCompat.getFont(requireContext(), R.font.pretendard_2)!!
        )


        binding.gymDetailGymTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setTabTypeface(tab, ResourcesCompat.getFont(tab.view.context, R.font.pretendard_2))
                binding.appBarLayout.setExpanded(false, true)

                val fragment = childFragmentManager.findFragmentByTag("f" + binding.gymDetailGymVp.currentItem)
                fragment?.requireView()?.scrollTo(0,0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                setTabTypeface(tab, ResourcesCompat.getFont(tab.view.context, R.font.pretendard))
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                binding.appBarLayout.setExpanded(false, true)

                val fragment = childFragmentManager.findFragmentByTag("f" + binding.gymDetailGymVp.currentItem)
                fragment?.requireView()?.scrollTo(0,0)
            }
        })



        return binding.root
    }


    private fun initView() {
        // 스크롤에 따른 상단 바 설정
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val window = requireActivity().window
            when {
                // 모두 펴졌을 때
                verticalOffset == 0 -> {
                    window.statusBarColor =
                        ContextCompat.getColor(requireActivity(), R.color.transparent)
                    binding.toolbar.setBackgroundResource(R.color.transparent)
                    binding.gymDetailBackBtn.setColorFilter(Color.parseColor("#ffffff"))
                }
                // 모두 접혔을 때
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)
                    binding.toolbar.setBackgroundResource(R.color.white)
                    binding.gymDetailBackBtn.setColorFilter(Color.parseColor("#000000"))
                }
                else -> {
                    window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)
                    binding.toolbar.setBackgroundResource(R.color.white)
                    binding.gymDetailBackBtn.setColorFilter(Color.parseColor("#000000"))
                }
            }
        })


        // 헬스장 이미지 설정
        getGymImg()


    }

    private fun getGymImg() {
        val gymImgFm = childFragmentManager
        val gymImgLifecycle = viewLifecycleOwner.lifecycle
        val gymImgVPAdapter =
            GymImgVPAdapter(gymImgFm, gymImgLifecycle)

        // 헬스장 이미지 더미데이터
        gymImgVPAdapter.addFragment(GymImgFragment(R.drawable.ex_gym_img))
        gymImgVPAdapter.addFragment(GymImgFragment(R.drawable.temp))
        gymImgVPAdapter.addFragment(GymImgFragment(R.drawable.ex_gym_img))

        binding.gymDetailGymImgVp.adapter = gymImgVPAdapter

        // 초기 설정
        currentImgIndex = 0
        binding.gymDetailImgPrevBtn.visibility = View.GONE
        binding.gymDetailImgNextBtn.visibility = View.VISIBLE
        binding.gymDetailImgPositionTv.text =
            "${currentImgIndex + 1}/${gymImgVPAdapter.itemCount}"


        // 이미지 넘기기 버튼 클릭 시
        binding.gymDetailImgNextBtn.setOnClickListener {
            currentImgIndex++
            binding.gymDetailGymImgVp.currentItem = currentImgIndex
            binding.gymDetailImgPositionTv.text =
                "${currentImgIndex + 1}/${gymImgVPAdapter.itemCount}"

            if (currentImgIndex == gymImgVPAdapter.itemCount - 1) {
                binding.gymDetailImgNextBtn.visibility = View.GONE
                binding.gymDetailImgPrevBtn.visibility = View.VISIBLE
            } else {
                binding.gymDetailImgPrevBtn.visibility = View.VISIBLE
                binding.gymDetailImgNextBtn.visibility = View.VISIBLE
            }
        }

        binding.gymDetailImgPrevBtn.setOnClickListener {
            currentImgIndex--
            binding.gymDetailGymImgVp.currentItem = currentImgIndex
            binding.gymDetailImgPositionTv.text =
                "${currentImgIndex + 1}/${gymImgVPAdapter.itemCount}"

            if (currentImgIndex == 0) {
                binding.gymDetailImgPrevBtn.visibility = View.GONE
                binding.gymDetailImgNextBtn.visibility = View.VISIBLE
            } else {
                binding.gymDetailImgPrevBtn.visibility = View.VISIBLE
                binding.gymDetailImgNextBtn.visibility = View.VISIBLE
            }
        }

        binding.gymDetailGymImgVp.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                currentImgIndex = position

                binding.gymDetailImgPositionTv.text =
                    "${currentImgIndex + 1}/${gymImgVPAdapter.itemCount}"

                when (position) {
                    0 -> {
                        // First Page
                        binding.gymDetailImgPrevBtn.visibility = View.GONE
                        binding.gymDetailImgNextBtn.visibility = View.VISIBLE
                    }
                    gymImgVPAdapter.itemCount - 1 -> {
                        // Last Page
                        binding.gymDetailImgNextBtn.visibility = View.GONE
                        binding.gymDetailImgPrevBtn.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.gymDetailImgPrevBtn.visibility = View.VISIBLE
                        binding.gymDetailImgNextBtn.visibility = View.VISIBLE
                    }
                }
            }
        })

    }


    private fun setTabTypeface(tab: TabLayout.Tab, typeface: Typeface?) {
        for (i in 0 until tab.view.childCount) {
            val tabViewChild = tab.view.getChildAt(i)
            if (tabViewChild is TextView) tabViewChild.typeface = typeface
        }
    }

    private inner class GymImgVPAdapter(
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

    inner class GymDetailVPAdapter(childFragmentManager: FragmentManager, getLifecycle: Lifecycle) :
        FragmentStateAdapter(childFragmentManager, getLifecycle) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> GymInfoFragment()          // 헬스장 소개 탭
                else -> GymTrainerFragment()    // 트레이너 탭
            }
        }
    }


}

