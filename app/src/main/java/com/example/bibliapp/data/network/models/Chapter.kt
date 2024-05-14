package com.example.bibliapp.data.network.models

import android.text.Html

data class Chapter(
    val summary: ChapterSummary,
    val content: Html,
    val verseCount: Int,
    val nextId: String,
    val previousId: String,
    val copyright: String,
)
