package com.example.bibliapp.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Language(
    val id: String = "No id",
    val name: String = "No name",
    val scriptDirection: String = "LTR"
)
