package com.example.bibliapp.di

import com.example.bibliapp.data.network.BibleApiClient
import com.example.bibliapp.data.network.BibleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideBibleApiService(): BibleApiService {
        return BibleApiClient.bibleApiService;
    }
}