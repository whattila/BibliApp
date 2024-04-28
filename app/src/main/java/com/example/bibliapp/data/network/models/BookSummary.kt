package com.example.bibliapp.data.network.models

import androidx.compose.runtime.Immutable

@Immutable
data class BookSummary(
    val id: String,
    val abbreviation: String,
    val name: String
)
