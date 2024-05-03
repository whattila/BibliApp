package com.example.bibliapp.di

import android.content.Context
import com.example.bibliapp.data.database.AppDatabase
import com.example.bibliapp.data.database.dao.ChapterDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideChapterDAO(appDatabase: AppDatabase): ChapterDAO {
        return appDatabase.chapterDao()
    }
}