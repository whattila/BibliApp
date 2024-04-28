package com.example.bibliapp.data.network.models

import androidx.compose.runtime.Immutable

@Immutable
data class ChapterSummary(
    // TODO: hol a fejezet címe?! Van neki egyáltalán?
    val id: String,
    val position: Int
)
