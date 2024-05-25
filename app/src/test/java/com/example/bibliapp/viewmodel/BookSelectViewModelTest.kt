package com.example.bibliapp.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.Bible
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.BookSummary
import com.example.bibliapp.domain.Language
import com.example.bibliapp.feature.book_select.BibleUiState
import com.example.bibliapp.feature.book_select.BookSelectViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class BookSelectViewModelTest {

    @MockK
    private val mockRepository = mockk<BrowseRepository>()

    private val id = "7ea794434e9ea7ee-01"

    @Test
    fun bookSelectViewModel_fetchBible_happyPath() = runBlocking {
        val bible = Bible(
            summary = BibleSummary(
                id = "7ea794434e9ea7ee-01",
                name = "Biblica® Open Chinese Contemporary Bible Simplified 2022",
                nameLocal = "当代译本",
                abbreviation = "OCCB",
                abbreviationLocal = "OCCB",
                description = "Bible",
                descriptionLocal = "圣经",
                language = Language(id = "cmn", name = "Chinese, Mandarin", scriptDirection = "LTR")
            ),
            books = listOf(
                BookSummary(
                    id = "GEN",
                    name = "创世记"
                ),
                BookSummary(
                    id = "EXO",
                    name = "出埃及记"
                ),
                BookSummary(
                    id = "LEV",
                    name = "利未记"
                ),
                BookSummary(
                    id = "NUM",
                    name = "民数记"
                ),
            )
        )

        coEvery { mockRepository.getBible(id) } returns bible
        val viewModel = BookSelectViewModel(mockRepository)
        viewModel.fetchBible(id)
        assertEquals(BibleUiState.Success(bible), viewModel.bibleUiState)
    }

    @Test
    fun bookSelectViewModel_fetchBible_emptyBible() = runBlocking {
        coEvery { mockRepository.getBible(id) } returns Bible()
        val viewModel = BookSelectViewModel(mockRepository)
        viewModel.fetchBible(id)
        assertEquals(BibleUiState.Success(Bible()), viewModel.bibleUiState)
    }

    @Test
    fun bookSelectViewModel_fetchBible_HttpException() = runBlocking {
        coEvery { mockRepository.getBible(id) } throws IOException()
        val viewModel = BookSelectViewModel(mockRepository)
        viewModel.fetchBible(id)
        assertEquals(BibleUiState.Error, viewModel.bibleUiState)
    }
}