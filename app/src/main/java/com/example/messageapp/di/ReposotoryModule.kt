package com.example.messageapp.di

import android.content.Context
import com.example.messageapp.data.MessageProvider
import com.example.messageapp.repository.MessageDataSource
import com.example.messageapp.repository.MessageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
class ReposotoryModule {

    @Provides
    fun provideMessageProvider(@ApplicationContext context: Context) : MessageProvider {
        return MessageProvider(context)
    }

    @Provides
    fun provideMessageDataSource(provider: MessageProvider) : MessageDataSource {
        return MessageDataSource(provider)
    }

    @Provides
    fun provideMessageRepo(source: MessageDataSource) : MessageRepository {
        return MessageRepository(source)
    }
}