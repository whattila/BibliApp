package com.example.bibliapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bibliapp.navigation.MainScreen
import com.example.bibliapp.ui.theme.BibliAppTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics
        setContent {
            BibliAppTheme {
                MainScreen()
            }
        }
    }

    fun logChapterSave(bibleId: String, chapterId: String) {
        firebaseAnalytics.logEvent("chapter_save") {
            param("bible_id", bibleId)
            param("chapter_id", chapterId)
        }
    }
}