package com.example.exerciseday_android.ui.expert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.FragmentSelectBodyPartBinding


class SelectBodyPartFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentSelectBodyPartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectBodyPartBinding.inflate(inflater, container, false)


        // 운동할 신체 부위 버튼 선택 시, 세부 부위 선택 페이지로 이동
        binding.selectBodyPartArmBtn.setOnClickListener(this)
        binding.selectBodyPartLowerBodyBtn.setOnClickListener(this)
        binding.selectBodyPartAbdomenBtn.setOnClickListener(this)
        binding.selectBodyPartChestBtn.setOnClickListener(this)
        binding.selectBodyPartShoulderBtn.setOnClickListener(this)
        binding.selectBodyPartBodyBackBtn.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {

        when (v) {
//            binding.selectBodyPartBackBtn -> 코스(전문가, 커스텀) 선택 페이지로 이동!!
            binding.selectBodyPartArmBtn -> {
                sendBodyPart(binding.selectBodyPartArmBtn.text.toString())
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                    .commitAllowingStateLoss()
            }
            binding.selectBodyPartLowerBodyBtn -> {
                sendBodyPart(binding.selectBodyPartLowerBodyBtn.text.toString())
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                    .commitAllowingStateLoss()
            }
            binding.selectBodyPartAbdomenBtn -> {
                sendBodyPart(binding.selectBodyPartAbdomenBtn.text.toString())
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                    .commitAllowingStateLoss()
            }
            binding.selectBodyPartChestBtn -> {
                sendBodyPart(binding.selectBodyPartChestBtn.text.toString())
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                    .commitAllowingStateLoss()
            }
            binding.selectBodyPartShoulderBtn -> {
                sendBodyPart(binding.selectBodyPartShoulderBtn.text.toString())
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                    .commitAllowingStateLoss()
            }
            binding.selectBodyPartBodyBackBtn -> {
                sendBodyPart(binding.selectBodyPartBodyBackBtn.text.toString())
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SelectBodyDetailPartFragment())
                    .commitAllowingStateLoss()
            }
        }
    }

    // 선택한 신체 부위 전달
    private fun sendBodyPart(bodyPart: String) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putString("bodyPart", bodyPart)?.apply()
    }

}