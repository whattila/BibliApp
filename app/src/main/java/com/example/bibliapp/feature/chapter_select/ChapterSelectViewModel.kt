package com.example.bibliapp.feature.chapter_select

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.Book
import com.example.bibliapp.domain.BookSummary
import com.example.bibliapp.domain.ChapterSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChapterSelectViewModel @Inject constructor(
    browseRepository: BrowseRepository
) : ViewModel() {

    private val _book = mutableStateOf(
        Book(
            bookSummary = BookSummary(id = "book1", name = "Book1"),
            chapters = listOf(
                ChapterSummary(id = "c1", position = 1),
                ChapterSummary(id = "c2", position = 2)
            )
        )
    )

    // TODO: így kell visszaadni, ha azt szeretném, hogy a felület reagáljon a változásokra?
    fun getBook(): Book {
        return _book.value;
    }
}