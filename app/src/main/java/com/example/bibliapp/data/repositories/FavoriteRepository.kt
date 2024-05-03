package com.example.bibliapp.data.repositories

import com.example.bibliapp.data.database.dao.ChapterDAO
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    val chapterDAO: ChapterDAO
) {
}