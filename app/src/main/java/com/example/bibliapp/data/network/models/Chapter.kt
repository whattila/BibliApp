package com.example.bibliapp.data.network.models

import androidx.compose.runtime.Immutable

@Immutable
data class Chapter(
    // TODO: hol a fejezet címe?! Van neki egyáltalán?
    val summary: ChapterSummary,
    val content: String,
    val verseCount: Int,
    val nextId: String,
    val previousId: String,
    val copyright: String,
)
