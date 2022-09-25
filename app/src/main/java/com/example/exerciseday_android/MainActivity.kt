package com.example.exerciseday_android

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.databinding.ActivityMainBinding
import com.example.exerciseday_android.ui.MyReviewFragment
import com.example.exerciseday_android.ui.temp.CommunityFragment
import com.example.exerciseday_android.ui.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavV.selectedItemId = R.id.homeFragment

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavV.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.gymFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MapFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.mypageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


    // 로그인한 유저의 userIdx, jwt 받기
    private fun loadUserResult(): ArrayList<String> {
        var pref = applicationContext.getSharedPreferences("jwt", 0)
        var jwt = pref?.getString("jwt", "")
        var userIdx = pref?.getInt("userIdx", 0)

        var userResult = arrayListOf<String>(jwt.toString(), userIdx.toString())

        return userResult
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .commit()
    }
}