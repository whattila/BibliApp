package com.example.bibliapp.feature.chapter_select

import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.BrowseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChapterSelectViewModel @Inject constructor(
    browseRepository: BrowseRepository
) : ViewModel() {}