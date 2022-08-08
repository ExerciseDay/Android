package com.example.exerciseday_android.ui.join

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityPurposeSettingBinding


class PurposeSettingActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPurposeSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurposeSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.purposeSettingHomeTrainingView.setOnClickListener(this)
        binding.purposeSettingWeightControlView.setOnClickListener(this)
        binding.purposeSettingBuildingMuscleView.setOnClickListener(this)
        binding.purposeSettingTrainerPreparationView.setOnClickListener(this)
        binding.purposeSettingHealthCareView.setOnClickListener(this)
        binding.purposeSettingBodyProfileView.setOnClickListener(this)


        // 각 목적 버튼 클릭 시
        // #홈트레이닝
        binding.purposeSettingHomeTrainingView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.purposeSettingHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.purposeSettingHomeTrainingIv.setColorFilter(resources.getColor(R.color.white))
                    binding.purposeSettingHomeTrainingTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {

                    binding.purposeSettingHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingHomeTrainingIv.setColorFilter(R.color.black)
                    binding.purposeSettingHomeTrainingTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.purposeSettingHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingHomeTrainingIv.setColorFilter(R.color.black)
                    binding.purposeSettingHomeTrainingTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.purposeSettingHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingHomeTrainingIv.setColorFilter(R.color.black)
                    binding.purposeSettingHomeTrainingTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #체중조절
        binding.purposeSettingWeightControlView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.purposeSettingWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.purposeSettingWeightControlIv.setColorFilter(resources.getColor(R.color.white))
                    binding.purposeSettingWeightControlTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.purposeSettingWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingWeightControlIv.setColorFilter(R.color.black)
                    binding.purposeSettingWeightControlTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.purposeSettingWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingWeightControlIv.setColorFilter(R.color.black)
                    binding.purposeSettingWeightControlTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.purposeSettingWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingWeightControlIv.setColorFilter(R.color.black)
                    binding.purposeSettingWeightControlTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #근력기르기
        binding.purposeSettingBuildingMuscleView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.purposeSettingBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.purposeSettingBuildingMuscleIv.setColorFilter(resources.getColor(R.color.white))
                    binding.purposeSettingBuildingMuscleTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.purposeSettingBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingBuildingMuscleIv.setColorFilter(R.color.black)
                    binding.purposeSettingBuildingMuscleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.purposeSettingBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingBuildingMuscleIv.setColorFilter(R.color.black)
                    binding.purposeSettingBuildingMuscleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.purposeSettingBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingBuildingMuscleIv.setColorFilter(R.color.black)
                    binding.purposeSettingBuildingMuscleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #트레이너 준비
        binding.purposeSettingTrainerPreparationView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.purposeSettingTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.purposeSettingTrainerPreparationIv.setColorFilter(resources.getColor(R.color.white))
                    binding.purposeSettingTrainerPreparationTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.purposeSettingTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingTrainerPreparationIv.setColorFilter(R.color.black)
                    binding.purposeSettingTrainerPreparationTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.purposeSettingTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingTrainerPreparationIv.setColorFilter(R.color.black)
                    binding.purposeSettingTrainerPreparationTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.purposeSettingTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingTrainerPreparationIv.setColorFilter(R.color.black)
                    binding.purposeSettingTrainerPreparationTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #건강관리
        binding.purposeSettingHealthCareView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.purposeSettingHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.purposeSettingHealthCareIv.setColorFilter(resources.getColor(R.color.white))
                    binding.purposeSettingHealthCareTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.purposeSettingHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingHealthCareIv.setColorFilter(R.color.black)
                    binding.purposeSettingHealthCareTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.purposeSettingHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingHealthCareIv.setColorFilter(R.color.black)
                    binding.purposeSettingHealthCareTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.purposeSettingHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingHealthCareIv.setColorFilter(R.color.black)
                    binding.purposeSettingHealthCareTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #바디프로필
        binding.purposeSettingBodyProfileView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.purposeSettingBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.purposeSettingBodyProfileIv.setColorFilter(resources.getColor(R.color.white))
                    binding.purposeSettingBodyProfileTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.purposeSettingBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingBodyProfileIv.setColorFilter(R.color.black)
                    binding.purposeSettingBodyProfileTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.purposeSettingBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingBodyProfileIv.setColorFilter(R.color.black)
                    binding.purposeSettingBodyProfileTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.purposeSettingBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.purposeSettingBodyProfileIv.setColorFilter(R.color.black)
                    binding.purposeSettingBodyProfileTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.purpose_setting_home_training_view, R.id.purpose_setting_weight_control_view, R.id.purpose_setting_building_muscle_view,
            R.id.purpose_setting_trainer_preparation_view, R.id.purpose_setting_health_care_view, R.id.purpose_setting_body_profile_view
            -> startActivity(Intent(this, JoinCompleteActivity::class.java))
        }
    }

}