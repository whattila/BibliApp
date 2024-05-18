package com.example.bibliapp.feature.book_select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.Bible
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.BookSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSelectViewModel @Inject constructor(
    private val browseRepository: BrowseRepository
) : ViewModel() {
    var bibleUiState: BibleUiState by mutableStateOf(BibleUiState.Loading)
        private set

    fun fetchBible(bibleId: String) {
        viewModelScope.launch {
            bibleUiState = try {
                val result = browseRepository.getBible(bibleId)
                BibleUiState.Success(result)
            } catch (e: Exception) {
                BibleUiState.Error
            }
        }
    }
}