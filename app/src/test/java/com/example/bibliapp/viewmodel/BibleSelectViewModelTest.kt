package com.example.bibliapp.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.Language
import com.example.bibliapp.feature.bible_select.BibleListUiState
import com.example.bibliapp.feature.bible_select.BibleSelectViewModel
import com.example.bibliapp.feature.bible_select.SectionData
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class BibleSelectViewModelTest {

    @MockK
    private val mockRepository = mockk<BrowseRepository>()

    @Test
    fun bibleSelectViewModel_getBiblesSectioned_isSuccessEmptyListWhenBibleListIsEmpty() = runBlocking {
        coEvery { mockRepository.getAllBibles() } returns emptyList()
        val viewModel = BibleSelectViewModel(mockRepository)
        assertEquals(BibleListUiState.Success(emptyList()), viewModel.bibleListUiState)
    }

    @Test
    fun bibleSelectViewModel_getBiblesSectioned_isSuccessSameList() = runBlocking {
        val bibleList = listOf(
            BibleSummary(
                id = "472735b48a27b402-01",
                name = "The New Testament in Ahirani language",
                nameLocal = "प्रेम संदेश",
                abbreviation = "NTAii20",
                abbreviationLocal = "NTAii20",
                description = "The New Testament in Ahirani language, 2020",
                descriptionLocal = "प्रेम संदेश, नवा करार, 2020",
                language = Language(id = "ahr", name = "Ahirani", scriptDirection = "LTR")
            ),
            BibleSummary(
                id = "0c2ff0a5c8b9069c-01",
                name = "Nend Portions - Mark",
                nameLocal = "MAK Yakŋ Ohɨrand Ya Imbɨr Makɨv Mpamar",
                abbreviation = "NendNP03",
                abbreviationLocal = "NendNP03",
                description = "No description",
                descriptionLocal = "Mark in Nend",
                language = Language(id = "anh", name = "Nend", scriptDirection = "LTR")
            ),
            BibleSummary(
                id = "685d1470fe4d5c3b-01",
                name = "American Standard Version (Byzantine Text with Apocrypha)",
                nameLocal = "American Standard Version Byzantine Text with Apocrypha",
                abbreviation = "ASVBT",
                abbreviationLocal = "ASVBT",
                description = "No description",
                descriptionLocal = "No description",
                language = Language(id = "eng", name = "English", scriptDirection = "LTR")
            ),
            BibleSummary(
                id = "bba9f40183526463-01",
                name = "Berean Standard Bible",
                nameLocal = "English: Berean Standard Bible",
                abbreviation = "BSB",
                abbreviationLocal = "BSB",
                description = "Berean Standard Bible",
                descriptionLocal = "English: Berean Standard Bible",
                language = Language(id = "eng", name = "English", scriptDirection = "LTR")
            )
        )
        val sectionedList = listOf(
            SectionData(
                language = Language(id = "ahr", name = "Ahirani", scriptDirection = "LTR"),
                bibles = listOf(
                    BibleSummary(
                        id = "472735b48a27b402-01",
                        name = "The New Testament in Ahirani language",
                        nameLocal = "प्रेम संदेश",
                        abbreviation = "NTAii20",
                        abbreviationLocal = "NTAii20",
                        description = "The New Testament in Ahirani language, 2020",
                        descriptionLocal = "प्रेम संदेश, नवा करार, 2020",
                        language = Language(id = "ahr", name = "Ahirani", scriptDirection = "LTR")
                    )
                )
            ),
            SectionData(
                language = Language(id = "eng", name = "English", scriptDirection = "LTR"),
                bibles = listOf(
                    BibleSummary(
                        id = "685d1470fe4d5c3b-01",
                        name = "American Standard Version (Byzantine Text with Apocrypha)",
                        nameLocal = "American Standard Version Byzantine Text with Apocrypha",
                        abbreviation = "ASVBT",
                        abbreviationLocal = "ASVBT",
                        description = "No description",
                        descriptionLocal = "No description",
                        language = Language(id = "eng", name = "English", scriptDirection = "LTR")
                    ),
                    BibleSummary(
                        id = "bba9f40183526463-01",
                        name = "Berean Standard Bible",
                        nameLocal = "English: Berean Standard Bible",
                        abbreviation = "BSB",
                        abbreviationLocal = "BSB",
                        description = "Berean Standard Bible",
                        descriptionLocal = "English: Berean Standard Bible",
                        language = Language(id = "eng", name = "English", scriptDirection = "LTR")
                    )
                )
            ),
            SectionData(
                language = Language(id = "anh", name = "Nend", scriptDirection = "LTR"),
                bibles = listOf(
                    BibleSummary(
                        id = "0c2ff0a5c8b9069c-01",
                        name = "Nend Portions - Mark",
                        nameLocal = "MAK Yakŋ Ohɨrand Ya Imbɨr Makɨv Mpamar",
                        abbreviation = "NendNP03",
                        abbreviationLocal = "NendNP03",
                        description = "No description",
                        descriptionLocal = "Mark in Nend",
                        language = Language(id = "anh", name = "Nend", scriptDirection = "LTR")
                    )
                )
            )
        )

        coEvery { mockRepository.getAllBibles() } returns bibleList
        val viewModel = BibleSelectViewModel(mockRepository)
        assertEquals(BibleListUiState.Success(sectionedList), viewModel.bibleListUiState)
    }

    @Test
    fun bibleSelectViewModel_getBiblesSectioned_isErrorWhenException() = runBlocking {
        coEvery { mockRepository.getAllBibles() } throws NullPointerException()
        val viewModel = BibleSelectViewModel(mockRepository)
        assertEquals(BibleListUiState.Error, viewModel.bibleListUiState)
    }
}