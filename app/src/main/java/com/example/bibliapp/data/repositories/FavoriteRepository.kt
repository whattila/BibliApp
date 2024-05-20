package com.example.bibliapp.data.repositories

import com.example.bibliapp.data.database.dao.ChapterDAO
import com.example.bibliapp.domain.Chapter
import com.example.bibliapp.domain.ChapterHeader
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityScoped
class FavoriteRepository @Inject constructor(
    private val chapterDAO: ChapterDAO
) {
    fun getFavoriteChapters(): Flow<List<ChapterHeader>> =
        chapterDAO.getChapterHeaders().map { databaseHeaders ->
            databaseHeaders.map { it.toDomain() }
        }

    suspend fun getChapter(id: Long): Chapter =
        chapterDAO.getChapter(id).toDomain()

    suspend fun saveChapter(chapter: Chapter) {
        chapterDAO.insertChapter(
            com.example.bibliapp.data.database.entities.Chapter(
                reference = chapter.reference,
                content = chapter.content.toString(),
                verseCount = chapter.verseCount
            )
        )
    }

    suspend fun removeChapterByHeader(header: ChapterHeader) {
        val chapter = chapterDAO.getChapter(header.id)
        chapterDAO.deleteChapter(chapter)
    }
}