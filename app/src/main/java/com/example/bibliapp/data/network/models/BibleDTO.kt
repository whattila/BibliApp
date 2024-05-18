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
    fun toDomain(): BibleSummary {
        return BibleSummary(
            id = id,
            name = name ?: "No name available",
            nameLocal = nameLocal ?: "No name available",
            abbreviation = abbreviation ?: "No abbreviation available",
            abbreviationLocal = abbreviationLocal ?: "No abbreviation available",
            description = description ?: "No description available",
            descriptionLocal = descriptionLocal ?: "No description available",
        )
    }
}
