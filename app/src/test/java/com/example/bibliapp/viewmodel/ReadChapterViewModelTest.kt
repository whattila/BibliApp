package com.example.bibliapp.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.data.repositories.FavoriteRepository
import com.example.bibliapp.domain.Chapter
import com.example.bibliapp.feature.read_chapter.ChapterUiState
import com.example.bibliapp.feature.read_chapter.ReadChapterViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class ReadChapterViewModelTest {
    @MockK
    private val mockBrowseRepository = mockk<BrowseRepository>()
    @MockK
    private val mockFavoriteRepository = mockk<FavoriteRepository>()

    @Test
    fun readChapterViewModel_fetchChapter_databaseChapterIdIs0() = runBlocking {
        val dummyId = "dummy"
        coEvery { mockBrowseRepository.getChapter(dummyId, dummyId) } returns Chapter()
        val viewModel = ReadChapterViewModel(mockBrowseRepository, mockFavoriteRepository)
        viewModel.fetchChapter(dummyId, dummyId, 0)
        coVerify(exactly = 1) { mockBrowseRepository.getChapter(dummyId, dummyId) }
    }

    @Test
    fun readChapterViewModel_fetchChapter_databaseChapterIdIsNot0() = runBlocking {
        val dummyId: Long = 123
        coEvery { mockFavoriteRepository.getChapter(dummyId) } returns Chapter()
        val viewModel = ReadChapterViewModel(mockBrowseRepository, mockFavoriteRepository)
        viewModel.fetchChapter(null, null, dummyId)
        coVerify(exactly = 1) { mockFavoriteRepository.getChapter(dummyId) }
    }

    @Test
    fun readChapterViewModel_fetchChapter_everythingIsInvalid() = runBlocking {
        val viewModel = ReadChapterViewModel(mockBrowseRepository, mockFavoriteRepository)
        viewModel.fetchChapter(null, null, 0)
        assertEquals(ChapterUiState.Error, viewModel.chapterUiState)
    }
}