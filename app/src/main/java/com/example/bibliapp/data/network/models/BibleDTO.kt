package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.BibleSummary

data class BibleDTO(
    val id: String = "",
    val name: String? = "",
    val nameLocal: String? = "",
    val abbreviation: String? = "",
    val abbreviationLocal: String? = "",
    val description: String? = "",
    val descriptionLocal: String? = "",
    val type: String? = "",
    val language: LanguageDTO = LanguageDTO()
) {
    fun toDomain(): BibleSummary =
        BibleSummary(
            id = id,
            name = name ?: "No name",
            nameLocal = nameLocal ?: "No name",
            abbreviation = abbreviation ?: "No abbreviation",
            abbreviationLocal = abbreviationLocal ?: "No abbreviation",
            description = description ?: "No description",
            descriptionLocal = descriptionLocal ?: "No description",
            language = language.toDomain()
        )
}
