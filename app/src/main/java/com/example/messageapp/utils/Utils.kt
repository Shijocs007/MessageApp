package com.example.messageapp.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.messageapp.R
import com.example.messageapp.models.Message

object Utils {
    var channel_default = "DEFAULT"
    val message_notification_id = 1001



    fun formatDate(date: String?): String? {
        return DateFormat.format("dd/MM/yyyy hh:mm:ss", date!!.toLong()).toString()
    }

    fun createNotification(context: Context, message: Message, intent: Intent) {

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        var builder = NotificationCompat.Builder(context, channel_default)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(message.address)
            .setContentText(message.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(Utils.message_notification_id, builder.build())
        }

    }
}