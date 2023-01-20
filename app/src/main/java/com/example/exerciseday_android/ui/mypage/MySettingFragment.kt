package com.example.exerciseday_android.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentMySettingBinding
import com.example.exerciseday_android.ui.LogoutDialog
import com.example.exerciseday_android.ui.mypage.withdraw.WithdrawReasonActivity


class MySettingFragment : Fragment() {

    lateinit var binding: FragmentMySettingBinding
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMySettingBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로가기 눌렀을 때 동작할 코드
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.slide_out, R.anim.fade_in, R.anim.slide_out)
                    .replace(R.id.main_frm, MyPageFragment())
                    .commitAllowingStateLoss()
            }
        })

        binding.settingBackBtn.setOnClickListener {
            val myPageFragment = MyPageFragment()
            mainActivity.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out, R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.main_frm, myPageFragment)
                .commitAllowingStateLoss()
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

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                (context as MainActivity).supportFragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.slide_out, R.anim.none, R.anim.slide_out, R.anim.none)
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        callback.remove()
//    }
}