package com.example.exerciseday_android.ui.temp

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityGymDetailBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_gym_detail.*


class GymDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityGymDetailBinding

//    lateinit var gymInfoFragment: GymInfoFragment
//    lateinit var trainerInfoFragment: TrainerInfoFragment
    lateinit var scrollView : ScrollView
    lateinit var imgGym : ImageView
    lateinit var btnBack : ImageButton
    lateinit var btnImgNext : ImageButton
    lateinit var btnImgBack : ImageButton
    lateinit var textGymName : TextView
    lateinit var textGymAdd : TextView
    lateinit var textGymDistance : TextView
    lateinit var textGymStarValue : TextView
    lateinit var textImgNumber : TextView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym_detail)

        scrollView = findViewById(R.id.scrollView)

        imgGym = findViewById(R.id.imgGym)
        btnBack = findViewById(R.id.btnBack)
        btnImgBack = findViewById(R.id.btnImgBack)
        btnImgNext = findViewById(R.id.btnImgNext)

        textGymName = findViewById(R.id.textGymName)
        textGymAdd = findViewById(R.id.textGymAdd)
        textGymDistance = findViewById(R.id.textGymDistance)
        textGymStarValue = findViewById(R.id.textGymStarValue)
        textImgNumber = findViewById(R.id.textImgNumber)

        /* 투명 상태바 설정 */
        var window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        /* 뒤로가기 버튼 */
        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener{
            finish()
        }
        /* 헬스장 인덱스 넘겨받기 */
        val gymIdx = getSharedPreferences("gymIdx", Context.MODE_PRIVATE)

        /* 이미지 슬라이더 */
        var index = 1

        btnImgNext.setOnClickListener {
            index++
        }
        btnImgBack.setOnClickListener {
            index--
        }


        /* Tab 레이아웃 설정 */
//        gymInfoFragment = GymInfoFragment()
//        trainerInfoFragment = TrainerInfoFragment()

//        supportFragmentManager.beginTransaction().add(R.id.frameLayout, gymInfoFragment).commit()
//
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when(tab?.position) {
//                    0 -> { replaceView(gymInfoFragment) }
//                    else -> { replaceView(trainerInfoFragment) }
//                }
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//        })

    }

    /* Tab 레이아웃 전환 함수 */
    private fun replaceView(tab: Fragment) {
        var selectedFragment : Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, it).commit()
        }
    }

}