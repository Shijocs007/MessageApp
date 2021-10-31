package com.example.messageapp

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.messageapp.adapter.MessageListAdapter
import com.example.messageapp.databinding.ActivityMainBinding
import com.example.messageapp.viewmodels.MessageListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageListActivity : AppCompatActivity() {

    private val viewModel: MessageListViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    val messageAdapter = MessageListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        initObservers()

        viewModel.loadMessages()
    }

    private fun initObservers() {
        binding.apply {
            recyclerView.apply {
                adapter = messageAdapter
                layoutManager = LinearLayoutManager(context)
            }
            viewModel.messageList.observe(this@MessageListActivity) { result ->
                messageAdapter.submitData(this@MessageListActivity.lifecycle, result)
            }
        }
    }

    private fun initToolbar() {
        binding.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_menu)
                navigationIcon!!
                    .setColorFilter(resources.getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP)
                setTitleTextColor(resources.getColor(R.color.grey_80))
                setSupportActionBar(this)
                supportActionBar!!.title = "Messages"
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }
}