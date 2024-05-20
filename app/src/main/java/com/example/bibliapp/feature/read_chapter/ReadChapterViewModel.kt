package com.example.bibliapp.feature.read_chapter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.data.repositories.FavoriteRepository
import com.example.bibliapp.domain.Chapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: hogy döntjük el, hogy a fejezet mentve van-e vagy sem?
@HiltViewModel
class ReadChapterViewModel @Inject constructor(
    private val browseRepository: BrowseRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    var chapterUiState: ChapterUiState by mutableStateOf(ChapterUiState.Loading)
        private set

    fun fetchChapter(bibleId: String?, chapterId: String?, databaseChapterId: Long) {
        viewModelScope.launch {
            chapterUiState = try {
                val result = if (databaseChapterId == 0L) {
                    browseRepository.getChapter(bibleId!!, chapterId!!)
                } else {
                    favoriteRepository.getChapter(databaseChapterId)
                }
                ChapterUiState.Success(result)
            } catch (e: Exception) {
                ChapterUiState.Error
            }
        }
    }

    suspend fun saveChapter(chapter: Chapter) {
        favoriteRepository.saveChapter(chapter)
    }
}