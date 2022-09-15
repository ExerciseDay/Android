package com.example.exerciseday_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.databinding.ActivityMainBinding
import com.example.exerciseday_android.ui.temp.CommunityFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavV.selectedItemId = R.id.homeFragment

        initBottomNavigation()
        sendUserResult(loadUserResult())
    }

    private fun initBottomNavigation() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavV.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.recordFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MapFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.communityFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, CommunityFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
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

    // 로그인한 유저의 userIdx, jwt 전달
    private fun sendUserResult(userResult: ArrayList<String>) {
        var pref = applicationContext.getSharedPreferences("jwt", 0)
        var editor = pref?.edit()
        editor?.putString("jwt", userResult[0])?.apply()

        pref = applicationContext.getSharedPreferences("userIdx", 0)
        editor = pref?.edit()
        editor?.putInt("userIdx", userResult[1].toInt())?.apply()

    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragment)
            .commit()
    }
}