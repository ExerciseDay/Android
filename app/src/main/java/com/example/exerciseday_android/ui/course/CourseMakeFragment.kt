package com.example.exerciseday_android.ui.course

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.HomeFragment
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentCourseMakeBinding
import com.example.exerciseday_android.ui.course.custom.CustomStartFragment
import com.example.exerciseday_android.ui.course.expert.SelectBodyPartFragment

class CourseMakeFragment : Fragment() {
    lateinit var binding: FragmentCourseMakeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseMakeBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

        binding.courseMakeBackBtn.setOnClickListener {
            val homeFragment = HomeFragment()
            mainActivity.replaceFragment(homeFragment)
        }

        binding.courseMakeCustomBtn.setOnClickListener {
            val customStartFragment = CustomStartFragment()
            mainActivity.replaceFragment(customStartFragment)
        }

        binding.courseMakeExpertBtn.setOnClickListener {
//            val selectBodyPartFragment = SelectBodyPartFragment()
//            mainActivity.replaceFragment(selectBodyPartFragment)
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.main_frm, SelectBodyPartFragment())
                .commitAllowingStateLoss()
        }


        // 각 코스 버튼 touch event
        // 커스텀 코스
        binding.courseMakeCustomBtn.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.courseMakeCustomBtn.setBackgroundResource(R.drawable.img_and_text_btn_selected)
                    binding.courseMakeCustomIv.setColorFilter(
                        resources.getColor(
                            R.color.white,
                            null
                        )
                    )
                    binding.courseMakeCustomTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.courseMakeCustomBtn.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.courseMakeCustomIv.setColorFilter(R.color.black)
                    binding.courseMakeCustomTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.courseMakeCustomBtn.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.courseMakeCustomIv.setColorFilter(R.color.black)
                    binding.courseMakeCustomTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })

        // 전문가 코스
        binding.courseMakeExpertBtn.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.courseMakeExpertBtn.setBackgroundResource(R.drawable.img_and_text_btn_selected)
                    binding.courseMakeExpertIv.setColorFilter(
                        resources.getColor(
                            R.color.white,
                            null
                        )
                    )
                    binding.courseMakeExpertTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.courseMakeExpertBtn.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.courseMakeExpertIv.setColorFilter(R.color.black)
                    binding.courseMakeExpertTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.courseMakeExpertBtn.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.courseMakeExpertIv.setColorFilter(R.color.black)
                    binding.courseMakeExpertTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        return binding.root
    }
}