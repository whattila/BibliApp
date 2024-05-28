package com.example.bibliapp.feature.bible_select

import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.Language

data class SectionData(
    val language: Language,
    val bibles: List<BibleSummary>
)
