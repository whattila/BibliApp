package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Chapter(
    val title: String,
    val text: String
)
