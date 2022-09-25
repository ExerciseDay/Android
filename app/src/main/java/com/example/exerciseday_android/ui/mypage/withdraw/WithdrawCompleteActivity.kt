package com.example.exerciseday_android.ui.mypage.withdraw

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseday_android.APIS
import com.example.exerciseday_android.data.remote.mypage.WithdrawRes
import com.example.exerciseday_android.databinding.ActivityWithdrawCompleteBinding
import com.example.exerciseday_android.ui.ChangeDialog
import com.example.exerciseday_android.ui.DefaultDialog
import com.example.exerciseday_android.ui.login.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WithdrawCompleteActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.withdrawRemoveBtn.setOnClickListener {
            withdraw()
        }
    }

    private fun withdraw() {
        val intent = Intent(this, StartActivity::class.java)

        var spf = this.getSharedPreferences("jwt", MODE_PRIVATE)
        var jwt = spf.getString("jwt", "none").toString()

        spf = this.getSharedPreferences("userIdx", MODE_PRIVATE)
        var userIdx = spf?.getInt("userIdx", 0)

        Log.d("server", userIdx.toString()+"-------"+jwt)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.39.184.186:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: APIS = retrofit.create(APIS::class.java)

        server.withdraw(jwt, userIdx!!).enqueue(object :
            Callback<WithdrawRes> {
            override fun onFailure(call: Call<WithdrawRes>, t: Throwable) {
                Log.d("server", t.toString())
            }

            override fun onResponse(call: Call<WithdrawRes>, response: Response<WithdrawRes>) {
                Log.d("server", response.body().toString())
                if (response.body()!!.code == 1000) {
                        val completeDialog = ChangeDialog(this@WithdrawCompleteActivity)
                        completeDialog.show("회원 탈퇴가 완료되었습니다")
                        completeDialog.btnClickListener {
                            startActivity(intent)
                        }
                } else {
                    val failDialog = DefaultDialog(this@WithdrawCompleteActivity)
                    failDialog.show("회원 탈퇴에 실패했습니다.\n상담 번호를 통해 문의해주세요")
                }
            }
        })
    }
}