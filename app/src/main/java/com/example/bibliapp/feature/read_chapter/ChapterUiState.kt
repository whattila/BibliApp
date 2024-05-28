package com.example.bibliapp.feature.read_chapter

import com.example.bibliapp.domain.Chapter

interface ChapterUiState {
    data class Success(val result: Chapter): ChapterUiState
    object Error: ChapterUiState
    object Loading: ChapterUiState
}