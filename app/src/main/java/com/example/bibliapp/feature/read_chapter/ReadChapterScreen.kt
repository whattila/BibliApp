package com.example.bibliapp.feature.read_chapter

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadChapterScreen (
    bibleId: String?,
    chapterId: String?,
    databaseChapterId: Long?,
    viewModel: ReadChapterViewModel
){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Viewing: getChapter() not implemented yet!") },
                actions = {
                    TextButton(onClick = { /*TODO: save and delete*/ }
                    ) {
                        Text("SAVE") // TODO: SAVE if not saved to favorites, else DELETE
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Home Screen", style = MaterialTheme.typography.headlineMedium
        )
    }
}