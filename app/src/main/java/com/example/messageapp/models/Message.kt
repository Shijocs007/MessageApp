package com.example.messageapp.models


data class Message(val id : String?, val address : String?, val person : String?,
                   val date : String?, val dateHeader : String?, val type : String?, val subject: String?, val body : String?,
var isHeader : Boolean = false, var header : String? = "")

