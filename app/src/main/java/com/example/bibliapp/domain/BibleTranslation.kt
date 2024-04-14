package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class BibleTranslation(
    val code: String,
    val bookTitles: List<String>
)
