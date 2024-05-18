package com.example.bibliapp.data.database.entities

import androidx.room.ColumnInfo

data class ChapterHeader(
    // TODO: Tényleg van title?
    @ColumnInfo(name = "chapterId") val chapterId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "verseCount") val verseCount: Int
)
