package com.example.bibliapp.repository

import android.text.Html
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bibliapp.data.database.dao.ChapterDAO
import com.example.bibliapp.data.database.entities.Chapter
import com.example.bibliapp.data.database.entities.ChapterHeader
import com.example.bibliapp.data.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

object MockDatabase : ChapterDAO {
    private val chapters = mutableListOf(
        Chapter(
            id = 1,
            reference = "EVANGELO DI S. LUCA",
            content = "<p class=\"mt2\">EVANGELO</p><p class=\"mt1\">DI SAN LUCA</p>",
            verseCount = 0
        )
    )
    override fun getChapterHeaders(): Flow<List<ChapterHeader>> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapter(id: Long): Chapter =
        chapters.find { it.id == id } ?: Chapter(reference = "", content = "", verseCount = -1)

    override suspend fun insertChapter(chapter: Chapter) {
        chapters.add(chapter)
    }

    override suspend fun deleteChapter(chapter: Chapter) {
        chapters.remove(chapter)
    }

}

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class FavoriteRepositoryTest {
    private val newId: Long = 0
    private val newChapter = com.example.bibliapp.domain.Chapter(
        reference = "EVANGELO DI S. MARCO",
        content = Html.fromHtml("<p class=\"mt2\">EVANGELO</p><p class=\"mt1\">DI SAN MARCO</p>", 0),
        verseCount = 0
    )

    @Test
    fun favoriteRepository_isContentSameAfterGet(): Unit = runBlocking {
        val repository = FavoriteRepository(MockDatabase)
        repository.saveChapter(newChapter)
        assertEquals(newChapter.content, repository.getChapter(newId).content)
    }

    @Test
    fun favoriteRepository_removeChapterByHeader_happyPath(): Unit = runBlocking {
        val header = com.example.bibliapp.domain.ChapterHeader(
            id = 1,
            reference = "EVANGELO DI S. LUCA",
            verseCount = 0
        )
        val repository = FavoriteRepository(MockDatabase)
        repository.removeChapterByHeader(header)
        assertEquals(com.example.bibliapp.domain.Chapter(), repository.getChapter(header.id))
    }
}