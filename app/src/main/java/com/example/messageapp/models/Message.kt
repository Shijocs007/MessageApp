package com.example.messageapp.models


data class Message(val id : String? =null, val address : String? =null, val person : String? =null,
                   val date : String? =null, val dateHeader : String? =null, val type : String? =null,
                   val subject: String? = null, val body : String? = null,
var isHeader : Boolean = false, var header : String? = "")

