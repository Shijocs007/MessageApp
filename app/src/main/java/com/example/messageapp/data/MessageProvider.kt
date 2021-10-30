package com.example.messageapp.data

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.Telephony
import com.example.messageapp.models.Message

class MessageProvider(val context: Context) {

    private lateinit var resolver : ContentResolver
    init {
            resolver = context.contentResolver
    }
    @SuppressLint("Range")
    fun getMessages(position: Int): List<Message>? {

        val messageList = mutableListOf<Message>()
        val cursor = resolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null)

        cursor?.apply {
            while (moveToNext()) {
                val id = getString(getColumnIndex(Telephony.Sms._ID))
                val address = getString(getColumnIndex(Telephony.Sms.ADDRESS))
                val person = getString(getColumnIndex(Telephony.Sms.PERSON))
                val date = getString(getColumnIndex(Telephony.Sms.DATE))
                val subject = getString(getColumnIndex(Telephony.Sms.SUBJECT))
                val body = getString(getColumnIndex(Telephony.Sms.BODY))
                val type = getString(getColumnIndex(Telephony.Sms.TYPE))

                messageList.add(Message(id, address, person, date, type, subject, body))
//                val type = when(Integer.parseInt(getString(getColumnIndex(Telephony.Sms.TYPE)))) {
//                    Telephony.Sms.MESSAGE_TYPE_INBOX -> "Inbox"
//                    Telephony.Sms.MESSAGE_TYPE_SENT -> "Sent"
//                    Telephony.Sms.MESSAGE_TYPE_OUTBOX -> "Outbox"
//                    else -> "Inbox"
//                }

            }
            close()
        }

        return messageList
    }
}