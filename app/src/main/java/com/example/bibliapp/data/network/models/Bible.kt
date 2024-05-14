package com.example.bibliapp.data.network.models

data class Bible(
    val summary: BibleSummary,
    val copyright: String,
    val books: List<BookSummary>
)
