package com.example.bibliapp.domain

import android.text.Html
import androidx.compose.runtime.Immutable

@Immutable
data class Chapter(
    val summary: ChapterSummary,
    val content: String,
    val verseCount: Int,
    val copyright: String
)
