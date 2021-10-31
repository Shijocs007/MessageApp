package com.example.messageapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import com.example.messageapp.activity.MessageDetailsActivity
import com.example.messageapp.models.Message
import com.example.messageapp.utils.Utils
import com.google.gson.Gson
import java.lang.Exception


class IncomingSms : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.extras

        try {
            if (bundle != null) {
                val pdusObj = bundle["pdus"] as Array<Any>?
                for (i in pdusObj!!.indices) {
                    val currentMessage: SmsMessage =
                        SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                    val phoneNumber: String = currentMessage.getDisplayOriginatingAddress()
                    val message: String = currentMessage.getDisplayMessageBody()
                    val date = Utils.formatDate(currentMessage.timestampMillis.toString())

                    val msg = Message(address = phoneNumber, date = date, body = message)

                    val intent = Intent(context, MessageDetailsActivity::class.java).apply {
                        putExtra("data", Gson().toJson(msg))
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }

                    if (context != null) {
                        Utils.createNotification(context,msg, intent)
                    }
                }
            }
        } catch (e: Exception) {
        }

    }
}