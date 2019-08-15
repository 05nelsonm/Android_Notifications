package sats.stackemhigh.androidnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn_get_notification.setOnClickListener {
            // Create an intent for the notification to open the app
            val contentIntent = Intent(this, MainActivity::class.java)
            val pendingContentIntent = PendingIntent.getActivity(this, 0, contentIntent, FLAG_ONE_SHOT)

            // Channel ID (needed outside of `if` statements)
            val channelId = "$packageName.simplechan"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Check for build version greater than or equal to version Oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Channel Notification"
                val importance = IMPORTANCE_HIGH
                val description = "Description Description Description"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description

                notificationManager.createNotificationChannel(channel)
            }

            // Will always happen independent of build version
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(IMPORTANCE_HIGH)
                .setContentTitle("Notification Notification")
                .setContentText("Content Content Content")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setColor(Color.GREEN)
                .setDefaults(1)
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }


    }
}

