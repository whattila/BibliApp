package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class ChapterSummary(
    val id: String,
    val position: Int
)
