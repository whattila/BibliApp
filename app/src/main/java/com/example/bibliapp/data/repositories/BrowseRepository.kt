package com.example.bibliapp.data.repositories

import com.example.bibliapp.data.network.BibleApiService
import javax.inject.Inject

class BrowseRepository @Inject constructor(
    val bibleApiService: BibleApiService
) {

}