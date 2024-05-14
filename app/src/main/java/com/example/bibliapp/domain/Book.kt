package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Book(
    val bookSummary: BookSummary,
    val chapters: List<ChapterSummary>
)
