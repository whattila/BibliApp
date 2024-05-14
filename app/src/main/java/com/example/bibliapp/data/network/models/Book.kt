package com.example.bibliapp.data.network.models

data class Book(
    val bookSummary: BookSummary,
    val chapters: List<ChapterSummary>
)
