package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.Bible

fun createDomainBible(bibleSummary: BibleDTO, books: List<BookSummaryDTO>): Bible {
    return Bible(
        summary = bibleSummary.toDomain(),
        books = books.map {it.toDomain()}
    )
}