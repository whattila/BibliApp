package com.example.bibliapp.feature.chapter_select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliapp.data.repositories.BrowseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterSelectViewModel @Inject constructor(
    private val browseRepository: BrowseRepository
) : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    // happy path + hiba (keressünk valami ritkább kivételt!)
    fun fetchBook(bibleId: String, bookId: String) {
        viewModelScope.launch {
            bookUiState = try {
                val result = browseRepository.getBook(bibleId, bookId)
                BookUiState.Success(result)
            } catch (e: Exception) {
                BookUiState.Error
            }
        }
    }
}