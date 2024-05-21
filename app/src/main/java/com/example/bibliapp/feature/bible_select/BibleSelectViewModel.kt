package com.example.bibliapp.feature.bible_select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.domain.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.LinkedHashMap
import javax.inject.Inject

// Az eredeti példában itt SavedStateHandle van. Az mire jó? Kell az nekünk?
@HiltViewModel
class BibleSelectViewModel @Inject constructor(
    private val browseRepository: BrowseRepository
) : ViewModel() {
    var bibleListUiState: BibleListUiState by mutableStateOf(BibleListUiState.Loading)
        private set
    init {
        getBiblesSectioned()
    }

    private fun getBiblesSectioned() {
        viewModelScope.launch {
            bibleListUiState = try {
                val result = browseRepository.getAllBibles()
                val sectionedResult = createSectionsByLanguage(result)
                BibleListUiState.Success(sectionedResult)
            } catch (e: Exception) {
                BibleListUiState.Error
            }
        }
    }

    private fun createSectionsByLanguage(bibleList: List<BibleSummary>): List<SectionData> {
        val sectionedBibles = LinkedHashMap<Language, MutableList<BibleSummary>>()
        bibleList.forEach {
            if ( ! sectionedBibles.containsKey(it.language)) {
                sectionedBibles[it.language] = mutableListOf()
            }
            sectionedBibles[it.language]?.add(it)
        }
        val sectionList = mutableListOf<SectionData>()
        sectionedBibles.forEach { (language, bibles) ->
            bibles.sortBy { it.abbreviationLocal }
            sectionList.add(SectionData(language = language, bibles = bibles))
        }
        sectionList.sortBy { it.language.name }
        return sectionList.toList() // Ezt még rendezhetnénk nyelv alapján
    }
}