package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class BookSummary(
    val id: String = "",
    val name: String = ""
)
