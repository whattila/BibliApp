package com.example.bibliapp.di

import com.example.bibliapp.data.database.dao.ChapterDAO
import com.example.bibliapp.data.network.BibleApiService
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.data.repositories.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideBrowseRepository(bibleApiService: BibleApiService): BrowseRepository {
        return BrowseRepository(bibleApiService);
    }

    @Provides
    @ViewModelScoped
    fun provideFavoriteRepository(chapterDAO: ChapterDAO): FavoriteRepository {
        return FavoriteRepository(chapterDAO);
    }
}