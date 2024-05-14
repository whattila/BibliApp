package com.example.bibliapp.domain

import android.text.Html
import androidx.compose.runtime.Immutable

@Immutable
data class Chapter(
    val summary: ChapterSummary,
    val content: Html,
    val verseCount: Int
)
