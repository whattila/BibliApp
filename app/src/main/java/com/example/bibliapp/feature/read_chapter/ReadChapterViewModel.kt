package com.example.bibliapp.feature.read_chapter

import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.BrowseRepository
import com.example.bibliapp.data.repositories.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReadChapterViewModel @Inject constructor(
    browseRepository: BrowseRepository,
    favoriteRepository: FavoriteRepository
) : ViewModel() {}