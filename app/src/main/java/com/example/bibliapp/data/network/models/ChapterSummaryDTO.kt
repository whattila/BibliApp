package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.ChapterSummary

data class ChapterSummaryDTO(
    val id: String,
    val number: String?
) {
    fun toDomain(): ChapterSummary {
        return ChapterSummary(
            id = id,
            number = number ?: "No number"
        )
    }
}
