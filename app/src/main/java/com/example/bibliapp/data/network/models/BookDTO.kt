package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.Book
import com.example.bibliapp.domain.BookSummary

data class BookDTO(
    val id: String = "",
    val name: String? = "",
    val chapters: List<ChapterSummaryDTO>? = emptyList()
) {
    fun toDomain(): Book {
        return Book(
            bookSummary = BookSummary(
                id = id,
                name = name ?: "No name available"
            ),
            chapters = chapters?.map { it.toDomain() } ?: emptyList()
        )
    }
}
