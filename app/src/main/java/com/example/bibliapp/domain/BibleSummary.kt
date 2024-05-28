package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class BibleSummary(
    val id: String = "",
    val name: String = "",
    val nameLocal: String = "",
    val abbreviation: String = "",
    val abbreviationLocal: String = "",
    val description: String = "",
    val descriptionLocal: String = "",
    val language: Language = Language()
)
