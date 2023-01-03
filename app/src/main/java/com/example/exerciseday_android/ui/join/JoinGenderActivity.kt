package com.example.exerciseday_android.ui.join

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityJoinGenderBinding


class JoinGenderActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityJoinGenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinGenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)

        // 뒤로 가기
        binding.joinGenderBackBtn.setOnClickListener(this)

        // 각 성별 버튼 클릭 시
        binding.joinGenderMaleView.setOnClickListener(this)
        binding.joinGenderFemaleView.setOnClickListener(this)


        // 각 성별 버튼 touch event
        // 남성
        binding.joinGenderMaleView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGenderMaleView.setBackgroundResource(R.drawable.img_and_text_btn_selected)
                    binding.joinGenderMaleIv.setColorFilter(
                        resources.getColor(
                            R.color.white,
                            null
                        )
                    )
                    binding.joinGenderMaleTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGenderMaleView.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.joinGenderMaleIv.setColorFilter(R.color.black)
                    binding.joinGenderMaleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGenderMaleView.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.joinGenderMaleIv.setColorFilter(R.color.black)
                    binding.joinGenderMaleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // 여성
        binding.joinGenderFemaleView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGenderFemaleView.setBackgroundResource(R.drawable.img_and_text_btn_selected)
                    binding.joinGenderFemaleIv.setColorFilter(
                        resources.getColor(
                            R.color.white,
                            null
                        )
                    )
                    binding.joinGenderFemaleTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGenderFemaleView.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.joinGenderFemaleIv.setColorFilter(R.color.black)
                    binding.joinGenderFemaleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGenderFemaleView.setBackgroundResource(R.drawable.img_and_text_btn_unselected)
                    binding.joinGenderFemaleIv.setColorFilter(R.color.black)
                    binding.joinGenderFemaleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.join_gender_back_btn -> finish()

            R.id.join_gender_male_view -> {
                sendJoinGender(binding.joinGenderMaleTv.text.toString())
            }
            R.id.join_gender_female_view -> {
                sendJoinGender(binding.joinGenderFemaleTv.text.toString())
            }
        }
    }

    private fun sendJoinGender(gender: String) {
        val joinInfoList = intent.extras!!.getStringArrayList("join")

        val joinGender: String = gender

        joinInfoList!!.add(joinGender)

        val intent = Intent(this, JoinGoalActivity::class.java)
        intent.putStringArrayListExtra("join", joinInfoList)

        startActivity(intent)
    }
}