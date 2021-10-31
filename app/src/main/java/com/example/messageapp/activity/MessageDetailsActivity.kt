package com.example.messageapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.messageapp.R
import com.example.messageapp.databinding.ActivityMessageDetailsBinding
import com.example.messageapp.models.Message
import com.google.gson.GsonBuilder

class MessageDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMessageDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_details)

        binding = ActivityMessageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("data")
        var message = GsonBuilder().create().fromJson(data, Message::class.java)

        initToolbar(message)
        initViews(message)
    }

    private fun initViews(message: Message) {
        binding.apply {
            date.text = message.date
            description.text = message.body
        }

    }

    private fun initToolbar(message: Message) {
        binding.toolbar.apply {
            setSupportActionBar(this)
            setTitleTextColor(resources.getColor(R.color.grey_80))
            supportActionBar?.title = message.address
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationOnClickListener {
                finish()
            }

        }
    }
}