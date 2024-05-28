package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class ChapterHeader(
    val id: Long,
    val reference: String,
    val verseCount: Int
)
