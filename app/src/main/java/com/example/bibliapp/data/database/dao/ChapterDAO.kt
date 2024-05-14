package com.example.bibliapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliapp.data.database.entities.Chapter
import com.example.bibliapp.data.database.entities.ChapterHeader

@Dao
interface ChapterDAO {
    @Query("SELECT chapterId, title, verseCount FROM chapter")
    fun getChapterHeaders(): List<ChapterHeader>

    @Query("SELECT * FROM chapter WHERE chapterId = :chapterId")
    fun getChapter(chapterId: Long): Chapter

    @Insert
    fun insertChapter(chapter: Chapter)

    @Delete
    fun deleteChapter(chapter: Chapter)
}