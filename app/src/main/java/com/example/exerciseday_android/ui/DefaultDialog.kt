package com.example.exerciseday_android.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.databinding.DialogDefaultBinding

class DefaultDialog(private val context: AppCompatActivity) {
    private lateinit var binding: DialogDefaultBinding
    private val dialog = Dialog(context)

    fun show(text: String) {
        binding = DialogDefaultBinding.inflate(context.layoutInflater)
        dialog.setContentView(binding.root)

        //배경 색 날리기
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //창 크기
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)

        dialog.show()

        binding.dialogDefaultContentTv.text = text

        binding.dialogDefaultBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}