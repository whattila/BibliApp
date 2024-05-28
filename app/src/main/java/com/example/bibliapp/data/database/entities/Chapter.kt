package com.example.bibliapp.data.database.entities

import android.text.Html
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bibliapp.domain.Chapter

@Entity(tableName = "chapter")
data class Chapter(
    // Bár kapunk az API-tól egy id-t, az szerintem nem egyedi globálisan, így itt kell egy másik
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "reference") val reference: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "verseCount") val verseCount: Int,
) {
    fun toDomain(): Chapter =
        Chapter(
            databaseId = id,
            reference = reference,
            content = Html.fromHtml(content, 0),
            verseCount = verseCount
        )
}
