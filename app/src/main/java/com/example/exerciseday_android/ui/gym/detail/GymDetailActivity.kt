package com.example.exerciseday_android.ui.gym.detail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.MapFragment
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityGymDetailBinding
import com.example.exerciseday_android.databinding.ActivityGymImgDetailBinding
import com.example.exerciseday_android.databinding.ActivityGymImgListBinding
import com.example.exerciseday_android.databinding.FragmentGymDetailBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class GymDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityGymDetailBinding

    private val gymDetailTab = arrayListOf("헬스장 소개", "트레이너")
    private var currentImgIndex = 0
    private var gymImageViews = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGymDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기
        binding.gymDetailBackBtn.setOnClickListener {
            // 헬스장 메인 페이지(MapFragment)로 이동
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        initView()


        // 헬스장 이미지 클릭 시
        binding.gymDetailGymIv.setOnClickListener {
            val intent = Intent(this, GymImgListActivity::class.java)
            startActivity(intent)
        }

        // [운동 세부 페이지] 헬스장 소개, 트레이너 - TabLayout, ViewPager2 연결
//        val gymDetailFm = fragmentManager
//        val gymDetailLifecycle = viewLifecycleOwner.lifecycle
        val gymDetailVPAdapter =
            GymDetailVPAdapter(this)
        binding.gymDetailGymVp.adapter = gymDetailVPAdapter

        TabLayoutMediator(binding.gymDetailGymTb, binding.gymDetailGymVp) { tab, position ->
            tab.text = gymDetailTab[position]
        }.attach()


    }

    private fun initView() {
        // 스크롤에 따른 상단 바 설정
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val window = this.window
            when {
                // 모두 펴졌을 때
                verticalOffset == 0 -> {
                    window.statusBarColor =
                        ContextCompat.getColor(this, R.color.transparent)
                    binding.toolbar.setBackgroundResource(R.color.transparent)
                    binding.gymDetailBackBtn.setColorFilter(Color.parseColor("#ffffff"))
                }
                // 모두 접혔을 때
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.white)
                    binding.toolbar.setBackgroundResource(R.color.white)
                    binding.gymDetailBackBtn.setColorFilter(Color.parseColor("#000000"))
                }
                else -> {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.white)
                    binding.toolbar.setBackgroundResource(R.color.white)
                    binding.gymDetailBackBtn.setColorFilter(Color.parseColor("#000000"))
                }
            }
        })



        // 헬스장 이미지 설정
        getGymImg()


    }

    private fun getGymImg() {
//        val gymImgFm = childFragmentManager
//        val gymImgLifecycle = viewLifecycleOwner.lifecycle
        val gymImgVPAdapter =
            GymImgVPAdapter(this)

        // 헬스장 이미지 더미데이터
        gymImgVPAdapter.addFragment(GymImgFragment(R.drawable.temp))
        gymImgVPAdapter.addFragment(GymImgFragment(R.drawable.ex_gym_img))
        gymImgVPAdapter.addFragment(GymImgFragment(R.drawable.ex_gym_img))

        binding.gymDetailGymIv.adapter = gymImgVPAdapter

        // 초기 설정
        currentImgIndex = 0
        binding.gymDetailImgPrevBtn.visibility = View.GONE
        binding.gymDetailImgNextBtn.visibility = View.VISIBLE
        binding.gymDetailImgPositionTv.text =
            "${currentImgIndex + 1}/${gymImgVPAdapter.itemCount}"

        // 버튼 클릭 시
        binding.gymDetailImgNextBtn.setOnClickListener {
            currentImgIndex++
            binding.gymDetailGymIv.currentItem = currentImgIndex
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
            binding.gymDetailGymIv.currentItem = currentImgIndex
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


    }

    private inner class GymImgVPAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val fragmentList: ArrayList<Fragment> = ArrayList()

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
            notifyItemInserted(fragmentList.size - 1)

        }

    }

    inner class GymDetailVPAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> GymInfoFragment()          // 헬스장 소개 탭
                else -> GymTrainerFragment()    // 트레이너 탭
            }
        }
    }


}
