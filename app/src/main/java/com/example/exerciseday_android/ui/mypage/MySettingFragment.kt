package com.example.exerciseday_android.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.GetCourseRes
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.data.remote.logout.LogoutRes
import com.example.exerciseday_android.databinding.FragmentMySettingBinding
import com.example.exerciseday_android.ui.ChangeDialog
import com.example.exerciseday_android.ui.LogoutDialog
import com.example.exerciseday_android.ui.mypage.withdraw.WithdrawReasonActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MySettingFragment : Fragment() {

    lateinit var binding: FragmentMySettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMySettingBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

        binding.settingBackBtn.setOnClickListener {
            val myPageFragment = MyPageFragment()
            mainActivity.replaceFragment(myPageFragment)
        }

        binding.settingLogoutBtn.setOnClickListener {
            val dialog = LogoutDialog(mainActivity)
            dialog.show()
        }

        binding.settingWithdrawalBtn.setOnClickListener {
            val intent = Intent(this.activity, WithdrawReasonActivity::class.java)
            mainActivity.startActivity(intent)
        }

        return binding.root
    }
}