package com.example.bibliapp.feature.read_chapter

import android.app.Activity
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bibliapp.MainActivity
import com.example.bibliapp.domain.Chapter
import com.example.bibliapp.ui.common.ErrorView
import com.example.bibliapp.ui.common.LoadingView
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.launch

// TODO: Törölni!
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
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceTint,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text("Viewing: ${ if (viewModel.chapterUiState is ChapterUiState.Success) (viewModel.chapterUiState as ChapterUiState.Success).result.reference else "" }") },
                actions = {
                    TextButton(onClick = {
                        coroutineScope.launch {
                            if (viewModel.chapterUiState is ChapterUiState.Success) {
                                viewModel.saveChapter((viewModel.chapterUiState as ChapterUiState.Success).result)
                                makeToast(context, "Chapter saved to favorites")
                                chapterId?.let { (context as MainActivity).logChapterSave(bibleId = bibleId!!, chapterId = chapterId) }
                                // TODO: delete if saved
                            }
                        }
                    }
                    ) {
                        Text(text = "SAVE",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge) // TODO: SAVE if not saved to favorites, else DELETE
                    }
                }
            )
        },
    ) { paddingValues -> when (viewModel.chapterUiState) {
            is ChapterUiState.Loading -> LoadingView(paddingValues)
            is ChapterUiState.Success -> ResultView(
                chapter = (viewModel.chapterUiState as ChapterUiState.Success).result,
                paddingValues = paddingValues,
                textSize = 24f,
                textColor = Color.Black.toArgb())
            is ChapterUiState.Error -> ErrorView(paddingValues)
        }
    }
}

@Composable
fun ResultView(
    chapter: Chapter,
    paddingValues: PaddingValues,
    textSize: Float, // Paraméter a szöveg méretéhez
    textColor: Int   // Paraméter a szöveg színéhez
) {
    AndroidView(
        modifier = Modifier.padding(paddingValues).verticalScroll(rememberScrollState()),
        factory = { context ->
            MaterialTextView(context).apply {
                this.textSize = textSize // Beállítjuk a szöveg méretét
                this.setTextColor(textColor) // Beállítjuk a szöveg színét
            }
        },
        update = {
            it.text = chapter.content
            it.textSize = textSize // Biztosítjuk, hogy az update-ben is beállítjuk
            it.setTextColor(textColor) // Biztosítjuk, hogy az update-ben is beállítjuk
        }
    )
}

// Function to generate a Toast
private fun makeToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
