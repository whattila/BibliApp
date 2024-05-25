package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.Bible

// TODO: unit test
fun createDomainBible(bibleSummary: BibleDTO, books: List<BookSummaryDTO>): Bible =
        Bible(summary = bibleSummary.toDomain(), books = books.map {it.toDomain()})