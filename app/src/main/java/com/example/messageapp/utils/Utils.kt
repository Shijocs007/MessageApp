package com.example.messageapp.utils

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.messageapp.R

object Utils {
    var channel_default = "DEFAULT"
    val message_notification_id = 1001


    fun createNotification(context: Context, title : String, message: String) {
        var builder = NotificationCompat.Builder(context, channel_default)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(Utils.message_notification_id, builder.build())
        }

    }
}