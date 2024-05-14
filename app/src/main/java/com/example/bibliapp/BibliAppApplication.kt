package com.example.bibliapp

import android.app.Application
import com.example.bibliapp.data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BibliAppApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }
}