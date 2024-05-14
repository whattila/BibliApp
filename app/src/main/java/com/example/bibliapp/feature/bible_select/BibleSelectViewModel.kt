package com.example.bibliapp.feature.bible_select

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.domain.BibleSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BibleSelectViewModel @Inject constructor(
    browseRepository: BrowseRepository
) : ViewModel() {

    private val _bibles = mutableStateListOf(
        BibleSummary(id = "t1", name = "Test1", nameLocal = "Test1", abbreviation = "Test Bible", abbreviationLocal = "Test Bible", description = "This is a test Bible object", descriptionLocal = "This is a test Bible object"),
        BibleSummary(id = "t2", name = "Test2", nameLocal = "Test2", abbreviation = "Test Bible", abbreviationLocal = "Test Bible", description = "This is a test Bible object", descriptionLocal = "This is a test Bible object")
    )

    fun getBibles(): List<BibleSummary> {
        return _bibles;
    }
}