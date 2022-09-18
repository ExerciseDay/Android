package com.example.exerciseday_android.ui.mypage.withdraw

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.R
import com.example.exerciseday_android.databinding.ActivityWithdrawReasonBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_map_spinner.view.*

class WithdrawReasonActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawReasonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawReasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Spinner 누르면 Modal Bottom Sheet 등장
        val withdrawReasonBottomSheetView =
            layoutInflater.inflate(R.layout.bottom_sheet_map_spinner, binding.root, false)
        val withdrawReasonBottomSheetDialog =
            BottomSheetDialog(this, R.style.CustomBottomSheetDialog)
        val withdrawReasonArray = resources.getStringArray(R.array.withdraw_reasons)
        withdrawReasonBottomSheetView.bottom_sheet_title_tv.text = "탈퇴 이유"
        withdrawReasonBottomSheetDialog.setContentView(withdrawReasonBottomSheetView)

        setBottomSheetView(
            withdrawReasonBottomSheetView,
            withdrawReasonArray,
            withdrawReasonBottomSheetDialog,
            binding.withdrawReasonContentEt
        )

        binding.withdrawBackBtn.setOnClickListener {
            finish()
        }

        binding.withdrawSubmitBtn.setOnClickListener {
            val intent = Intent(this, WithdrawCompleteActivity::class.java)
            startActivity(intent)
        }

        binding.withdrawReasonContentEt.setOnClickListener {
            withdrawReasonBottomSheetDialog.show()
        }
    }

    // Spinner 선택 설정
    private fun setBottomSheetView(
        bottomSheetView: View,
        arr: Array<String>,
        dialog: BottomSheetDialog,
        spinner: TextView
    ) {
        val bottomSheetListLayout: LinearLayout =
            bottomSheetView.findViewById(R.id.bottom_sheet_list_layout)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        val value = 16
        val dpValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            this.resources.displayMetrics
        ).toInt()


        for (i in arr.indices) {
            // Spinner array 항목 수만큼 TextView 동적 생성
            val bottomSheetListTv = TextView(this)

            bottomSheetListTv.layoutParams = layoutParams

            bottomSheetListTv.text = arr[i]
            bottomSheetListTv.gravity = Gravity.CENTER
            bottomSheetListTv.setTextSize(Dimension.SP, 17.0f)
            bottomSheetListTv.setTypeface(bottomSheetListTv.typeface, Typeface.NORMAL)
            bottomSheetListTv.setTextColor(resources.getColor(R.color.gray_950, null))
            bottomSheetListTv.setPadding(0, dpValue, 0, dpValue)
            bottomSheetListTv.setBackgroundResource(R.drawable.tv_gray50_to_gray300)

            bottomSheetListLayout.addView(bottomSheetListTv)

            // 클릭 시 해당 array 항목으로 Spinner 설정
            bottomSheetListTv.setOnClickListener {
                spinner.text = arr[i]
                binding.withdrawSubmitBtn.visibility = View.VISIBLE
                dialog.dismiss()
            }
        }
    }
}