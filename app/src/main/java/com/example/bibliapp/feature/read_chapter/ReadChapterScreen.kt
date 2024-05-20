package com.example.bibliapp.feature.read_chapter

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bibliapp.domain.Chapter
import com.example.bibliapp.ui.common.ErrorView
import com.example.bibliapp.ui.common.LoadingView
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.launch

// TODO: Menteni (és törölni) !
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadChapterScreen (
    bibleId: String?,
    chapterId: String?,
    databaseChapterId: Long,
    viewModel: ReadChapterViewModel
){
    LaunchedEffect(key1 = bibleId, key2 = chapterId) {
        viewModel.fetchChapter(bibleId, chapterId, databaseChapterId)
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Viewing: ${ if (viewModel.chapterUiState is ChapterUiState.Success) (viewModel.chapterUiState as ChapterUiState.Success).result.reference else "" }") },
                actions = {
                    TextButton(onClick = {
                        coroutineScope.launch {
                            if (viewModel.chapterUiState is ChapterUiState.Success) {
                                viewModel.saveChapter((viewModel.chapterUiState as ChapterUiState.Success).result)
                                // TODO: show toast after saved
                                // TODO: delete if saved
                            }
                        }
                    }
                    ) {
                        Text("SAVE") // TODO: SAVE if not saved to favorites, else DELETE
                    }
                }
            )
        },
    ) { paddingValues -> when (viewModel.chapterUiState) {
            is ChapterUiState.Loading -> LoadingView(paddingValues)
            is ChapterUiState.Success -> ResultView(
                (viewModel.chapterUiState as ChapterUiState.Success).result,
                paddingValues)
            is ChapterUiState.Error -> ErrorView(paddingValues)
        }
    }
}

@Composable
fun ResultView (
    chapter: Chapter,
    paddingValues: PaddingValues
) {
    AndroidView(
        modifier = Modifier.padding(paddingValues).verticalScroll(rememberScrollState()),
        factory = { MaterialTextView(it) },
        update = { it.text = chapter.content }
    )
}