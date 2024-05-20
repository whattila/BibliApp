package com.example.bibliapp.feature.bible_select

import com.example.bibliapp.domain.BibleSummary

interface BibleListUiState {
    data class Success(val result: List<SectionData>) : BibleListUiState
    object Error : BibleListUiState
    object Loading : BibleListUiState
}