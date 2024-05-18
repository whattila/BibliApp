package com.example.bibliapp.data.network.models

import android.text.Html

data class ChapterDTO(
    val reference: String?,
    val content: Html,
    val verseCount: Int?, // Ez biztos kell? Ha nem, töröljük!
)
