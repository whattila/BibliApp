package com.example.bibliapp.feature.book_select

import com.example.bibliapp.domain.Bible

interface BibleUiState {
    data class Success(val result: Bible) : BibleUiState
    object Error : BibleUiState
    object Loading : BibleUiState
}