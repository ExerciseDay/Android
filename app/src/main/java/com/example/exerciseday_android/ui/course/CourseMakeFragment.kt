package com.example.exerciseday_android.ui.course

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.HomeFragment
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentCourseMakeBinding
import com.example.exerciseday_android.ui.CourseDialog
import com.example.exerciseday_android.ui.course.custom.CustomStartFragment
import com.example.exerciseday_android.ui.course.expert.SelectBodyPartFragment


class CourseMakeFragment : Fragment() {
    lateinit var binding: FragmentCourseMakeBinding
    private var fabIsClicked = false

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
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
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


        // 물음표 fab click event
        binding.courseMakeFab.setOnClickListener {
            val dialog = CourseDialog(context as MainActivity, binding.courseMakeFab)
            if (!fabIsClicked) {
                fabIsClicked = true
                binding.courseMakeFab.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.gray_700, null))
                binding.courseMakeFab.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white, null))

                val layoutParams: WindowManager.LayoutParams =
                    (activity as MainActivity).window.attributes
                layoutParams.dimAmount = 0.75f
                (activity as MainActivity).window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                (activity as MainActivity).window.attributes = layoutParams
                dialog.show()

            } else {
                binding.courseMakeFab.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.white, null))
                binding.courseMakeFab.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.gray_500, null))
            }
            fabIsClicked = false
        }

        return binding.root
    }
}