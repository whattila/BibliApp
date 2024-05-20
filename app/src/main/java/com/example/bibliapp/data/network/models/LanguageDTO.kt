package com.example.bibliapp.data.network.models

import com.example.bibliapp.domain.Language

data class LanguageDTO(
    val id: String = "",
    val name: String = "",
    val scriptDirection: String = ""
) {
    fun toDomain(): Language =
        Language(
            id = id,
            name = name,
            scriptDirection = scriptDirection
        )
}
