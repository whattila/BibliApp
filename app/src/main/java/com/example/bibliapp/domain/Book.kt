package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Book(
    val title: String,
    val numberOfChapters: Int
)
