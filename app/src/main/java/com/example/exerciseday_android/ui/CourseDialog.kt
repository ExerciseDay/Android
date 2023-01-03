package com.example.exerciseday_android.ui

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.DialogCourseBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CourseDialog(private val context: AppCompatActivity, var fab: FloatingActionButton) {
    private lateinit var binding: DialogCourseBinding

    private val dialog = Dialog(context)

    fun show() {
        binding = DialogCourseBinding.inflate(context.layoutInflater)

        dialog.setContentView(binding.root)

        //배경 색 날리기
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window?.setFlags(
//            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//        )
//        dialog.window?.setFlags(
//            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
//            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
//        )
//        dialog.window?.setDimAmount(0F)

//        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        fab.parent.bringChildToFront(fab)
        //창 크기
        dialog.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        //창 위치
        dialog.window?.setGravity(Gravity.BOTTOM)

        val bottomValue = 175
        val dpValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            bottomValue.toFloat(),
            context.resources.displayMetrics
        ).toInt()

        val params = dialog.window?.attributes
        params?.y = dpValue
        dialog.window?.attributes = params

        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        dialog.show()
        fab.bringToFront()


        dialog.setOnDismissListener {
            fab.imageTintList =
                ColorStateList.valueOf(context.resources.getColor(R.color.white, null))
            fab.backgroundTintList =
                ColorStateList.valueOf(context.resources.getColor(R.color.gray_500, null))
        }

    }
}