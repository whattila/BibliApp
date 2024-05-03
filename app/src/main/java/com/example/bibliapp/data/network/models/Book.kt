package com.example.bibliapp.data.network.models

import androidx.compose.runtime.Immutable

@Immutable
data class Book(
    val bookSummary: BookSummary,
    val chapters: List<ChapterSummary>
)
