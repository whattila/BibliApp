package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Bible(
    val summary: BibleSummary = BibleSummary(),
    val books: List<BookSummary> = emptyList()
)
