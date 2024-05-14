package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Bible(
    val summary: BibleSummary,
    val books: List<BookSummary>
)
