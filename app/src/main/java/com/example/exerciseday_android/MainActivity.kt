package com.example.exerciseday_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.databinding.ActivityLoginBinding
import com.example.exerciseday_android.databinding.ActivityMainBinding

import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavV.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.recordFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, RecordFragment())
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
//                R.id.mapFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, MapFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }
//                R.id.settingFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, SettingFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }
            }
            false
        }
    }
}