package com.example.bibliapp.feature.chapter_select

import com.example.bibliapp.domain.Book

sealed interface BookUiState {
    data class Success(val result: Book) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}
