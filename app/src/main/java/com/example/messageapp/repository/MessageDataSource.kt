package com.example.messageapp.repository

import androidx.paging.PagingSource
import com.example.messageapp.data.MessageProvider
import com.example.messageapp.models.Message
import java.io.IOException
import java.lang.Exception

private const val UNSPLASH_STARTING_PAGE_INDEX = 1
val POST_PER_PAGE = 20

class MessageDataSource(private val provider : MessageProvider
) : PagingSource<Int, Message>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Message> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        try {
            var messages = provider.getMessages((position -1)* POST_PER_PAGE)

            return LoadResult.Page(
                data = messages!!,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (messages.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}