package com.example.bibliapp.feature.book_select

import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.BrowseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookSelectViewModel @Inject constructor(
    browseRepository: BrowseRepository
) : ViewModel() {}