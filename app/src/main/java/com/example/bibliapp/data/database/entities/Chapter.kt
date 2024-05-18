package com.example.bibliapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapter")
data class Chapter(
    // TODO: Mit tudunk ezek közül tényleg kiszedni a kapott adatokból? Mire van szükség?
    // Bár kapunk az API-tól egy id-t, az szerintem nem egyedi globálisan, így itt kell egy másik
    @PrimaryKey(autoGenerate = true) val chapterId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "verseCount") val verseCount: Int,
    @ColumnInfo(name = "copyright") val copyright: String
)
