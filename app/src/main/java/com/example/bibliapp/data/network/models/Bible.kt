package com.example.bibliapp.data.network.models

import androidx.compose.runtime.Immutable

@Immutable
data class Bible(
    val summary: BibleSummary,
    val copyright: String,
    val books: List<BookSummary>
)
