package com.example.messageapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig

class MessageRepository(private val source: MessageDataSource) {


    fun getMessageList() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { source }
    ).flow
}