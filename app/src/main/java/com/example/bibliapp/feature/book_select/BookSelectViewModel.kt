package com.example.bibliapp.feature.book_select

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.Bible
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.BookSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookSelectViewModel @Inject constructor(
    browseRepository: BrowseRepository
) : ViewModel() {

    private val _bible = mutableStateOf(
        Bible(
            summary = BibleSummary(id = "t1", name = "Test1", nameLocal = "Test1", abbreviation = "Test Bible", abbreviationLocal = "Test Bible", description = "This is a test Bible object", descriptionLocal = "This is a test Bible object"),
            books = listOf(
                BookSummary(id = "book1", name = "Book1"),
                BookSummary(id = "book2", name = "Book2")
            )
        )
    )

    // TODO: így kell visszaadni, ha azt szeretném, hogy a felület reagáljon a változásokra?
    fun getBible(): Bible {
        return _bible.value;
    }
}