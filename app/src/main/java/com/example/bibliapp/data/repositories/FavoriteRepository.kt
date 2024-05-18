package com.example.bibliapp.data.repositories

import com.example.bibliapp.data.database.dao.ChapterDAO
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

// TODO: itt kell konvertálni a domain és a database/entities osztályai közt!
@ActivityScoped
class FavoriteRepository @Inject constructor(
    val chapterDAO: ChapterDAO
) {

}