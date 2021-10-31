package com.example.messageapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast
import com.example.messageapp.utils.Utils
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
                    // Show Alert

                    if (context != null) {
                        Utils.createNotification(context, phoneNumber, message)
                    }
                } // end for loop
            } // bundle is null
        } catch (e: Exception) {
        }

    }
}