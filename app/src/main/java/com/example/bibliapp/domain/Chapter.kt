package com.example.bibliapp.domain

import android.text.Html
import android.text.Spanned
import androidx.compose.runtime.Immutable

@Immutable
data class Chapter(
    val databaseId: Long = 0,
    val reference: String = "",
    val content: Spanned = Html.fromHtml("", 0), // lehetne itt valami jobb?
    val verseCount: Int = -1,
)
