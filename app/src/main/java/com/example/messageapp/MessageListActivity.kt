package com.example.messageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.messageapp.viewmodels.MessageListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageListActivity : AppCompatActivity() {

    private val viewModel: MessageListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadMessages()
    }
}