package com.example.bibliapp.data.network.models

import android.text.Html
import com.example.bibliapp.domain.Chapter

data class ChapterDTO(
    val reference: String?,
    val content: String,
    val verseCount: Int?, // Ez biztos kell? Ha nem, töröljük!
) {
    fun toDomain(): Chapter {
        return Chapter(
            reference = reference ?: "No reference",
            content = Html.fromHtml(content, 0),
            verseCount = verseCount ?: -1
        )
    }
}
