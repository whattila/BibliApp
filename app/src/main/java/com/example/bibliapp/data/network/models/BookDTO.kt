package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.Book
import com.example.bibliapp.domain.BookSummary

// TODO: unit test
data class BookDTO(
    val id: String = "",
    val name: String? = "",
    val chapters: List<ChapterSummaryDTO>? = emptyList()
) {
    fun toDomain(): Book =
        Book(
            bookSummary = BookSummary(
                id = id,
                name = name ?: "No name"
            ),
            chapters = chapters?.map { it.toDomain() } ?: emptyList())
}
