package com.example.exerciseday_android.ui.join

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.*
import com.example.exerciseday_android.databinding.ActivityJoinGoalBinding


class JoinGoalActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityJoinGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 뒤로 가기
        binding.joinGoalBackBtn.setOnClickListener(this)

        // 각 목적 버튼 클릭 시
        binding.joinGoalHomeTrainingView.setOnClickListener(this)
        binding.joinGoalWeightControlView.setOnClickListener(this)
        binding.joinGoalBuildingMuscleView.setOnClickListener(this)
        binding.joinGoalTrainerPreparationView.setOnClickListener(this)
        binding.joinGoalHealthCareView.setOnClickListener(this)
        binding.joinGoalBodyProfileView.setOnClickListener(this)


        // 각 목적 버튼 touch event
        // #홈트레이닝
        binding.joinGoalHomeTrainingView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGoalHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.joinGoalHomeTrainingIv.setColorFilter(resources.getColor(R.color.white))
                    binding.joinGoalHomeTrainingTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {

                    binding.joinGoalHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalHomeTrainingIv.setColorFilter(R.color.black)
                    binding.joinGoalHomeTrainingTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGoalHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalHomeTrainingIv.setColorFilter(R.color.black)
                    binding.joinGoalHomeTrainingTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGoalHomeTrainingView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalHomeTrainingIv.setColorFilter(R.color.black)
                    binding.joinGoalHomeTrainingTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #체중조절
        binding.joinGoalWeightControlView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGoalWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.joinGoalWeightControlIv.setColorFilter(resources.getColor(R.color.white))
                    binding.joinGoalWeightControlTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.joinGoalWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalWeightControlIv.setColorFilter(R.color.black)
                    binding.joinGoalWeightControlTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGoalWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalWeightControlIv.setColorFilter(R.color.black)
                    binding.joinGoalWeightControlTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGoalWeightControlView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalWeightControlIv.setColorFilter(R.color.black)
                    binding.joinGoalWeightControlTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #근력기르기
        binding.joinGoalBuildingMuscleView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGoalBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.joinGoalBuildingMuscleIv.setColorFilter(resources.getColor(R.color.white))
                    binding.joinGoalBuildingMuscleTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.joinGoalBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalBuildingMuscleIv.setColorFilter(R.color.black)
                    binding.joinGoalBuildingMuscleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGoalBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalBuildingMuscleIv.setColorFilter(R.color.black)
                    binding.joinGoalBuildingMuscleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGoalBuildingMuscleView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalBuildingMuscleIv.setColorFilter(R.color.black)
                    binding.joinGoalBuildingMuscleTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #트레이너 준비
        binding.joinGoalTrainerPreparationView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGoalTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.joinGoalTrainerPreparationIv.setColorFilter(resources.getColor(R.color.white))
                    binding.joinGoalTrainerPreparationTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.joinGoalTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalTrainerPreparationIv.setColorFilter(R.color.black)
                    binding.joinGoalTrainerPreparationTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGoalTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalTrainerPreparationIv.setColorFilter(R.color.black)
                    binding.joinGoalTrainerPreparationTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGoalTrainerPreparationView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalTrainerPreparationIv.setColorFilter(R.color.black)
                    binding.joinGoalTrainerPreparationTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #건강관리
        binding.joinGoalHealthCareView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGoalHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.joinGoalHealthCareIv.setColorFilter(resources.getColor(R.color.white))
                    binding.joinGoalHealthCareTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.joinGoalHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalHealthCareIv.setColorFilter(R.color.black)
                    binding.joinGoalHealthCareTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGoalHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalHealthCareIv.setColorFilter(R.color.black)
                    binding.joinGoalHealthCareTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGoalHealthCareView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalHealthCareIv.setColorFilter(R.color.black)
                    binding.joinGoalHealthCareTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })


        // #바디프로필
        binding.joinGoalBodyProfileView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // PRESSED
                    binding.joinGoalBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_selected)
                    binding.joinGoalBodyProfileIv.setColorFilter(resources.getColor(R.color.white))
                    binding.joinGoalBodyProfileTv.setTextColor(Color.parseColor("#ffffff"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.joinGoalBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalBodyProfileIv.setColorFilter(R.color.black)
                    binding.joinGoalBodyProfileTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    // RELEASED
                    binding.joinGoalBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalBodyProfileIv.setColorFilter(R.color.black)
                    binding.joinGoalBodyProfileTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }

                MotionEvent.ACTION_CANCEL -> {
                    binding.joinGoalBodyProfileView.setBackgroundResource(R.drawable.purpose_setting_btn_unselected)
                    binding.joinGoalBodyProfileIv.setColorFilter(R.color.black)
                    binding.joinGoalBodyProfileTv.setTextColor(Color.parseColor("#000000"))

                    return@OnTouchListener true
                }
            }
            false
        })

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.join_goal_back_btn -> finish()

            R.id.join_goal_home_training_view -> {
                sendJoinGoal(binding.joinGoalHomeTrainingTv.text.toString())
            }
            R.id.join_goal_weight_control_view -> {
                sendJoinGoal(binding.joinGoalWeightControlTv.text.toString())
            }
            R.id.join_goal_building_muscle_view -> {
                sendJoinGoal(binding.joinGoalBuildingMuscleTv.text.toString())
            }
            R.id.join_goal_trainer_preparation_view -> {
                sendJoinGoal(binding.joinGoalTrainerPreparationTv.text.toString())
            }
            R.id.join_goal_health_care_view -> {
                sendJoinGoal(binding.joinGoalHealthCareTv.text.toString())
            }
            R.id.join_goal_body_profile_view -> {
                sendJoinGoal(binding.joinGoalBodyProfileTv.text.toString())
            }
        }
    }


    private fun sendJoinGoal(goal: String) {
        val joinInfoList = intent.extras!!.getStringArrayList("join")

        val goal: String = goal

        joinInfoList!!.add(goal)

        val intent = Intent(this, JoinCompleteActivity::class.java)
        intent.putStringArrayListExtra("join", joinInfoList)

        startActivity(intent)
    }

}