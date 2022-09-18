package com.example.exerciseday_android.ui

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.MainActivity
import com.example.exerciseday_android.data.remote.logout.LogoutRes
import com.example.exerciseday_android.databinding.DialogLogoutBinding
import com.example.exerciseday_android.ui.login.LoginActivity
import com.example.exerciseday_android.ui.login.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogoutDialog(private val context: AppCompatActivity) {
    private lateinit var binding: DialogLogoutBinding
    private val dialog = Dialog(context)

    fun show() {
        binding = DialogLogoutBinding.inflate(context.layoutInflater)
        dialog.setContentView(binding.root)

        //배경 색 날리기
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //창 크기
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)

        dialog.show()

        binding.closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        binding.logoutBtn.setOnClickListener {
            logout()
            dialog.dismiss()
        }
    }

    private fun logout() {
        val intent = Intent(context, StartActivity::class.java)

        var spf = context.getSharedPreferences("jwt", MODE_PRIVATE)
        var jwt = spf.getString("jwt", "none").toString()

        spf = context.getSharedPreferences("userIdx", MODE_PRIVATE)
        var userIdx = spf?.getInt("userIdx", 3)

        Log.d("server", userIdx.toString()+"-------"+jwt)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.logout(jwt, userIdx!!).enqueue(object :
            Callback<LogoutRes> {
            override fun onFailure(call: Call<LogoutRes>, t: Throwable) {
                Log.d("server", t.toString())
            }

            override fun onResponse(call: Call<LogoutRes>, response: Response<LogoutRes>) {
                Log.d("server", response.body().toString())
                when(response.body()!!.code) {
                    1000->{
                        val completeDialog = ChangeDialog(context)
                        completeDialog.show("로그아웃이 완료되었습니다")
                        completeDialog.btnClickListener {
                            context.startActivity(intent)
                        }
                    }
                    2002->{
                        val failDialog = DefaultDialog(context)
                        failDialog.show("로그아웃에 실패했습니다.\n상담 번호를 통해 문의해주세요")
                    }
                }
            }
        })
    }
}