package com.example.bibliapp.feature.bible_select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliapp.data.repositories.BrowseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Az eredeti példában itt SavedStateHandle van. Az mire jó? Kell az nekünk?
// TODO: a nyelveket hol kell kiválogatni? Itt vagy egy másik ViewModelben?
@HiltViewModel
class BibleSelectViewModel @Inject constructor(
    private val browseRepository: BrowseRepository
) : ViewModel() {
    var bibleListUiState: BibleListUiState by mutableStateOf(BibleListUiState.Loading)
        private set
    init {
        getBibles()
    }

    private fun getBibles() {
        viewModelScope.launch {
            bibleListUiState = try {
                val result = browseRepository.getAllBibles()
                BibleListUiState.Success(result)
            } catch (e: Exception) {
                BibleListUiState.Error
            }
        }
    }
}