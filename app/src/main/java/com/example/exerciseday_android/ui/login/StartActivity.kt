package com.example.exerciseday_android.ui.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.widget.ImageButton
//import android.widget.Toast
import com.example.exerciseday_android.databinding.ActivityStartBinding
import com.example.exerciseday_android.ui.terms.TermPersonalActivity
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApi
//import com.kakao.sdk.auth.LoginClient
//import com.kakao.sdk.auth.model.OAuthToken
//import com.kakao.sdk.common.util.Utility
//import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var keyHash = Utility.getKeyHash(this)
        Log.e("hash", "해시 키 값 : ${keyHash}")

        binding.startKakaoBtn.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                    if (error != null) {
                        Log.e("login", "로그인 실패", error)
                    } else if (token != null) {
                        Log.i("login", "로그인 성공 ${token.accessToken}")
                        UserApiClient.instance.me { user, error ->
                            if(error != null) {
                                Log.e("fail", "사용자 정보 요청 실패", error)
                            } else if (user != null) {
                                Log.e("success", "사용자정보 요청 성공" + "\n회원번호: ${user.id}" +
                                        "\n이메일: ${user.kakaoAccount?.email}" +
                                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                            }
                        }
                    }
                }
            } else {
                Log.e("로그", "카카오 로그인이 불가합니다.")
            }
        }

        binding.startNormalLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.startInfoBtn.setOnClickListener {
            val intent = Intent(this, TermPersonalActivity::class.java)
            startActivity(intent)
        }
    }
}
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_start)
//        // 로그인 정보 확인
//        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//            if (error != null) {
//                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
//            }
//            else if (tokenInfo != null) {
//                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
//            }
//        }
//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//                when {
//                    error.toString() == AccessDenied.toString() -> {
//                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == InvalidClient.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == InvalidGrant.toString() -> {
//                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == InvalidRequest.toString() -> {
//                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == InvalidScope.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == Misconfigured.toString() -> {
//                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == ServerError.toString() -> {
//                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == Unauthorized.toString() -> {
//                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
//                    }
//                    else -> { // Unknown
//                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            else if (token != null) {
//                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
//            }
//        }
//        val kakao_login_button = findViewById<ImageButton>(R.id.start_kakao_btn) // 로그인 버튼
//        kakao_login_button.setOnClickListener {
//            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
//                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
//            }else{
//                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
//            }
//        }
//    }
//}