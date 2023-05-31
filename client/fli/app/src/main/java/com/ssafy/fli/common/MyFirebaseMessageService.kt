package com.ssafy.fli.common

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssafy.fli.ApplicationClass
import com.ssafy.fli.R
import com.ssafy.fli.ui.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MyFirebaseMsgSvc_싸피"

class MyFirebaseMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
        // 새로운 토큰 수신 시 서버로 전송
        ApplicationClass.sharedPreferencesUtil.addToken(token)
        //MainActivity.uploadToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var messageTitle = ""
        var messageContent = ""
        var img = ""

        if (remoteMessage.notification != null) { // notification이 있는 경우 foreground처리
            //foreground
//            messageTitle = remoteMessage.notification!!.title.toString()
//            messageContent = remoteMessage.notification!!.body.toString()
//            img = remoteMessage.notification!!.imageUrl.toString()

        } else {  // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
            var data = remoteMessage.data
            Log.d(TAG, "data.message: ${data}")
            Log.d(TAG, "data.message: ${data.get("title")}")
            Log.d(TAG, "data.message: ${data.get("body")}")

            messageTitle = data.get("title").toString()
            messageContent = data.get("body").toString()
            img = data.get("image").toString()
        }

        val mainIntent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val mainPendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE)

        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                Constants.loadImage(img)
            }

            val builder1 =
                NotificationCompat.Builder(this@MyFirebaseMessageService, LoginActivity.channel_id)
                    .setSmallIcon(R.drawable.baseline_headset_mic_24)
                    .setContentTitle(messageTitle)
                    .setContentText(messageContent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setLargeIcon(bitmap)
                    .setContentIntent(mainPendingIntent)
                    .setFullScreenIntent(mainPendingIntent, true)

            NotificationManagerCompat.from(this@MyFirebaseMessageService).apply {
                notify(101, builder1.build())
            }
        }
    }
}