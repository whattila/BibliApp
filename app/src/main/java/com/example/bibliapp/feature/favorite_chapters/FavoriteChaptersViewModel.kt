package com.example.bibliapp.feature.favorite_chapters

import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.database.entities.ChapterHeader
import com.example.bibliapp.data.repositories.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteChaptersViewModel @Inject constructor(
    favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favoriteChapters = mutableListOf(
        ChapterHeader(chapterId = 1, title = "Test1", verseCount = 1),
        ChapterHeader(chapterId = 2, title = "Test2", verseCount = 1)
    )

    // Ez nem feltétlenül ideális hogy a ViewModel és a View az entitykkel dolgozik.
    // Az ideális az lenne, ha valahogy tudnánk konvertálni a domain és entity objektumok között!
    fun getFavoriteChapters(): List<ChapterHeader> {
        return _favoriteChapters;
    }
}