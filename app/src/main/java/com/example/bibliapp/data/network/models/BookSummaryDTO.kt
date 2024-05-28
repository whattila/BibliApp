package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.BookSummary

data class BookSummaryDTO(
    val id: String,
    val name: String?
) {
    fun toDomain(): BookSummary =
        BookSummary(
            id = id,
            name = name ?: "No name")
}
