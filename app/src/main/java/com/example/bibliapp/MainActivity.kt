package com.example.bibliapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bibliapp.navigation.MainScreen
import com.example.bibliapp.ui.theme.BibliAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BibliAppTheme {
                MainScreen()
            }
        }
    }
}