package sats.stackemhigh.androidnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 3871
        const val NOTIFICATION_KEY = "PLAKJSD09F2I9FUHB1Q"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get_notification.setOnClickListener {
            // Create an intent for the notification to open the app to the FullscreenActivity
            val contentIntent = Intent(this, FullscreenActivity::class.java)
            contentIntent.putExtra(NOTIFICATION_KEY, "Notification Tapped")
            val pendingContentIntent = PendingIntent.getActivity(this, 0, contentIntent, PendingIntent.FLAG_ONE_SHOT)

            // Channel ID (needed outside of `if` statements checking for version)
            val channelId = "$packageName.simplechan"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Check for build version greater than or equal to version Oreo to set channels
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Channel Notification"
                val importance = NotificationManager.IMPORTANCE_LOW
                val description = "Description Description Description"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description

                notificationManager.createNotificationChannel(channel)
            }

            // Will always happen independent of build version
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_LOW)
                .setContentTitle("Notification Notification")
                .setContentText("Content Content Content")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setColor(Color.GREEN)
                .setDefaults(1)
                .setContentIntent(pendingContentIntent)
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }


    }
}

