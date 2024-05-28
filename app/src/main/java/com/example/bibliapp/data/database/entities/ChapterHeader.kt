package com.example.bibliapp.data.database.entities

import androidx.room.ColumnInfo
import com.example.bibliapp.domain.ChapterHeader

data class ChapterHeader(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "reference") val reference: String,
    @ColumnInfo(name = "verseCount") val verseCount: Int
) {
    fun toDomain(): ChapterHeader =
        ChapterHeader(
            id = id,
            reference = reference,
            verseCount = verseCount
        )
}
