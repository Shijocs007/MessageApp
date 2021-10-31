package com.example.messageapp

import android.Manifest
import android.Manifest.permission
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.messageapp.adapter.MessageListAdapter
import com.example.messageapp.databinding.ActivityMainBinding

import com.example.messageapp.viewmodels.MessageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

import androidx.core.app.ActivityCompat





@AndroidEntryPoint
class MessageListActivity : AppCompatActivity() {

    private val viewModel: MessageListViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    val messageAdapter = MessageListAdapter()
    val REQUEST_PERMISSION_CODE = 1007

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        initObservers()
        if(isPermissionsGranted()) {
            viewModel.loadMessages()
        } else {
            requestPermissions()
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_PERMISSION_CODE -> {
                viewModel.loadMessages()
            }
        }
    }

    // Checking permission is enabled or not using function starts from here.
    fun isPermissionsGranted(): Boolean {
        val read_sms_permission = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_SMS)
        val send_sms_permission =
            ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.SEND_SMS)
        val receive_sms_permission = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.RECEIVE_SMS)
        return read_sms_permission == PackageManager.PERMISSION_GRANTED && send_sms_permission == PackageManager.PERMISSION_GRANTED && receive_sms_permission == PackageManager.PERMISSION_GRANTED
    }

    //Permission function starts from here
    private fun requestPermissions() {

        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS
            ), REQUEST_PERMISSION_CODE
        )
    }
}