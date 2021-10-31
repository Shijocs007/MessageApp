package com.example.messageapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.messageapp.models.Message
import com.example.messageapp.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MessageRepository
) : ViewModel()  {

    val messageList = MutableLiveData<PagingData<Message>>()

     fun loadMessages() {
         viewModelScope.launch {
             repository.getMessageList().cachedIn(viewModelScope).collect {
                 messageList.value = it
             }
         }
    }

}