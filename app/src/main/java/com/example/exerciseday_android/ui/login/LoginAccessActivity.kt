package com.example.exerciseday_android.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.exerciseday_android.databinding.ActivityLoginAccessBinding
import com.example.exerciseday_android.ui.terms.TermLocationActivity
import java.util.*


class LoginAccessActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginAccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginAccessCheckBtn.setOnClickListener {

//            // 위치 권한 요청
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(
//                    this,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                var permissions = arrayOf(
//                    android.Manifest.permission.ACCESS_FINE_LOCATION,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION
//                )
//                ActivityCompat.requestPermissions(this, permissions, MY_PERMISSION_ACCESS_ALL)
//            } else {
//                val intent = Intent(this, StartActivity::class.java)
//                startActivity(intent)
//            }


            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        binding.loginAccessLocationBtn.setOnClickListener {
            val intent = Intent(this, TermLocationActivity::class.java)
            startActivity(intent)
        }


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            0 -> {
                // 권한이 비어있는 경우 에러
                if(grantResults.isEmpty()){
                    throw RuntimeException("Empty Permission Result")
                }
                // 거부된 권한이 있는지 확인한다
                var isPermitted= true
                val deniedPermission = ArrayList<String>()
                for((id, result) in grantResults.withIndex()){
                    if(result == PackageManager.PERMISSION_DENIED){
                        isPermitted = false
                        deniedPermission.add(permissions[id])
                    }
                }
                // 권한이 모두 충족된 경우 다이얼로그를 보여준다
                if(isPermitted){
                    Log.d("권한 충족","권한 충족")

                    val intent = Intent(this, StartActivity::class.java)
                    startActivity(intent)
//                    showRecordDialog()
                }
                else{
                    Log.d("거부 선택","거부 선택")
                    // 거부만 선택한 경우
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){

                        // 권한이 필요하다는 토스트 메시지를 띄운다
                        Toast.makeText(this, "권한이 없어 앱을 실행할 수 없습니다.", Toast.LENGTH_LONG).show()
                        // 권한을 다시 요청한다
                        requestPermissions(deniedPermission.toArray(arrayOfNulls<String>(deniedPermission.size)), 0)
                    }
                    // 거부 및 다시보지 않기를 선택한 경우
                    else{
                        // 권한 설정으로 이동할 수 있도록 알림창을 띄운다
                        showDialogToGetPermission(this)
//                        super.showDialogToGetPermission(this)
                    }
                }
            }
        }
    }


    // 직접 권한 설정을 하기 위한 알림창
    fun showDialogToGetPermission(context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("권한설정")
            .setMessage("허세영어의 모든 기능을 사용하기 위해 녹음 및 외부 스토리지 접근 권한이 필요합니다." +
                    "확인을 눌러 권한 설정창으로 이동한 뒤 설정을 완료해주세요")
            .setPositiveButton("예"){ dialog, i->
                val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        builder.setNegativeButton("아니요"){ dialog, i ->

        }
        val dialog = builder.create()
        dialog.show()
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode === MY_PERMISSION_ACCESS_ALL) {
//            if (grantResults.isNotEmpty()) {
//                for (grant in grantResults) {
//                    if (grant != PackageManager.PERMISSION_GRANTED) {
////                        ActivityCompat.requestPermissions(this, permissions, MY_PERMISSION_ACCESS_ALL)
//                        Toast.makeText(this, "권한이 없어 앱을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
//                        requestPermissions(
//                            permissions.toArray(arrayOfNulls<String>(deniedPermission.size)),
//                            0
//                        )
////                        exitProcess(0) // 앱 종료
//                    } else {
//                        val intent = Intent(this, StartActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//            }
//        }
//    }
}