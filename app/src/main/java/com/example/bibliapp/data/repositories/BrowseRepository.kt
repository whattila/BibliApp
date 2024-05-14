package com.example.bibliapp.data.repositories

import com.example.bibliapp.data.network.BibleApiService
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BrowseRepository @Inject constructor(
    val bibleApiService: BibleApiService
) {

}