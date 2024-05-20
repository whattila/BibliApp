package com.example.bibliapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bibliapp.data.database.entities.Chapter
import com.example.bibliapp.data.database.entities.ChapterHeader
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDAO {
    @Query("SELECT id, reference, verseCount FROM chapter")
    fun getChapterHeaders(): Flow<List<ChapterHeader>>

    @Query("SELECT * FROM chapter WHERE id = :id")
    suspend fun getChapter(id: Long): Chapter // nem biztos hogy ez így jó, lehet hogy Flow kell suspend nélkül!

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapter: Chapter)

    @Delete
    suspend fun deleteChapter(chapter: Chapter)
}