package com.example.bibliapp.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.Book
import com.example.bibliapp.domain.BookSummary
import com.example.bibliapp.domain.ChapterSummary
import com.example.bibliapp.feature.chapter_select.BookUiState
import com.example.bibliapp.feature.chapter_select.ChapterSelectViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class ChapterSelectViewModelTest {

    @MockK
    private val mockRepository = mockk<BrowseRepository>()

    @Test
    fun chapterSelectViewModel_fetchBook_happyPath() = runBlocking {
        val book = Book(
            bookSummary = BookSummary(
                id = "LUK",
                name = "EVANGELO DI S. LUCA"
            ),
            chapters = listOf(
                ChapterSummary(
                    id = "LUK.intro",
                    number = "intro"
                ),
                ChapterSummary(
                    id = "LUK.1",
                    number = "1"
                ),
                ChapterSummary(
                    id = "LUK.2",
                    number = "2"

                )
            )
        )
        val bibleId = "41f25b97f468e10b-01"
        val bookId = "LUK"

        coEvery { mockRepository.getBook(bibleId, bookId) } returns book
        val viewModel = ChapterSelectViewModel(mockRepository)
        viewModel.fetchBook(bibleId, bookId)
        assertEquals(BookUiState.Success(book), viewModel.bookUiState)
    }
}