package com.ssafy.fli.ui.login

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.fli.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.fli.R
import com.ssafy.fli.databinding.ActivityLoginBinding
import com.ssafy.fli.model.Id
import com.ssafy.fli.model.User
import com.ssafy.fli.network.RetrofitUtil
import com.ssafy.fli.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_싸피"
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = sharedPreferencesUtil.getUser()

        if (user.id != "") {//로그인 상태
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
            setLoginUi()
        }


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "FCM 토큰 얻기에 실패하였습니다.", task.exception)
                return@OnCompleteListener
            }
            // token log 남기기
            Log.d(TAG, "token: ${task.result?:"task.result is null"}")
            if(task.result != null){
                uploadToken(task.result!!)
            }
        })
        createNotificationChannel(channel_id, "ssafy")


    }


    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(id, name, importance)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val notificationManager: NotificationManager
                = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun setLoginUi() {
        binding.logInBtn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            googleLoginLauncher.launch(signInIntent)
        }
    }

    private var googleLoginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                getGoogleInfo(task)
            }
        }

    private fun getGoogleInfo(completedTask: Task<GoogleSignInAccount>) {
        try {//성공
            val account = completedTask.getResult(ApiException::class.java)
            val googleId = Id(account.email.toString(), 0)
            CoroutineScope(Dispatchers.IO).launch {
                RetrofitUtil.userService.login(
                    User(
                        account.familyName + account.givenName,
                        googleId.id, googleId.sns, sharedPreferencesUtil.getToken()
                    )
                )
                Log.e("싸피",sharedPreferencesUtil.getToken())
                sharedPreferencesUtil.addUser(googleId)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }

        } catch (e: ApiException) { //실패
            Log.w("싸피", "signInResult:failed code=" + e.statusCode)
        }
    }


    companion object{
        // Notification Channel ID
        const val channel_id = "ssafy_channel"
        // retrofit  수업 후 network 에 업로드 할 수 있도록 구성
        fun uploadToken(token:String){
            // 새로운 토큰 수신 시 서버로 전송

//            storeService.uploadToken(token).enqueue(object : Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    if(response.isSuccessful){
//                        val res = response.body()
//                        Log.d(TAG, "onResponse: $res")
//                    } else {
//                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
//                    }
//                }
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.d(TAG, t.message ?: "토큰 정보 등록 중 통신오류")
//                }
//            })
        }
    }
}