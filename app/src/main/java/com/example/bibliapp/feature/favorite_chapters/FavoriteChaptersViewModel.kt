package com.example.bibliapp.feature.favorite_chapters

import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.FavoriteRepository
import com.example.bibliapp.domain.Chapter
import com.example.bibliapp.domain.ChapterHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteChaptersViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    val favoriteChapters: Flow<List<ChapterHeader>> = favoriteRepository.getFavoriteChapters()

    suspend fun removeChapterByHeader(header: ChapterHeader) {
        favoriteRepository.removeChapterByHeader(header)
    }
}