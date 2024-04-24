package com.example.bibliapp.feature.favorite_chapters

import androidx.lifecycle.ViewModel
import com.example.bibliapp.data.repositories.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteChaptersViewModel @Inject constructor(
    favoriteRepository: FavoriteRepository
) : ViewModel() {}