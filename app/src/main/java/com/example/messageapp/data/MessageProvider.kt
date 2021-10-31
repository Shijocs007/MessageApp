package com.example.messageapp.data

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.Telephony
import android.text.format.DateFormat
import android.text.format.DateUtils
import com.example.messageapp.models.Message
import com.example.messageapp.utils.Utils

class MessageProvider(val context: Context) {

    private var resolver : ContentResolver = context.contentResolver

    /**
     * get List of Messages from content provide
     * default items per page is 20
     * @param position the offset from the query will start
     */
    @SuppressLint("Range")
    fun getMessages(position: Int): List<Message>? {

        val messageList = mutableListOf<Message>()
        val cursor = resolver.query(Telephony.Sms.CONTENT_URI, null, null, null, "date DESC LIMIT 19 OFFSET " + position)

        cursor?.apply {
            while (moveToNext()) {
                val id = getString(getColumnIndex(Telephony.Sms._ID))
                val address = getString(getColumnIndex(Telephony.Sms.ADDRESS))
                val person = getString(getColumnIndex(Telephony.Sms.PERSON))
                val date = Utils.formatDate(getString(getColumnIndex(Telephony.Sms.DATE)))
                val dateHeader = formatDateHeader(getString(getColumnIndex(Telephony.Sms.DATE)))
                val subject = getString(getColumnIndex(Telephony.Sms.SUBJECT))
                val body = getString(getColumnIndex(Telephony.Sms.BODY))
                val type = getString(getColumnIndex(Telephony.Sms.TYPE))

                messageList.add(Message(id, address, person, date,dateHeader, type, subject, body))
            }
            close()
        }

        return getMessageListWithHeader(messageList)
    }

    /**
     * get the header string from date in milli secs
     */
    private fun formatDateHeader(date: String?): String? {
        return DateUtils.getRelativeTimeSpanString(date!!.toLong(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)
            .toString()
    }

    private fun getMessageListWithHeader(messageList: MutableList<Message>): List<Message> {

        val result = mutableListOf<Message>()
        val hashMap = mutableMapOf<String, MutableList<Message>>()

        for(message in messageList) {
            if(hashMap.containsKey(message.dateHeader)) {
                val list = hashMap.get(message.dateHeader)
                list?.add(message)
                hashMap.put(message.dateHeader!!, list!!)
            } else {
                val list = mutableListOf(message)
                message.dateHeader?.let { hashMap.put(it, list) }
            }
        }

        hashMap.forEach{ (key, value) ->
            val msg = Message(isHeader = true, header = key)
            result.add(msg)
            result.addAll(value)
        }

        return result
    }
}