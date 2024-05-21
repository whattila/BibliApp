package com.example.bibliapp.feature.chapter_select

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.bibliapp.domain.Book
import com.example.bibliapp.ui.common.ErrorView
import com.example.bibliapp.ui.common.LoadingView
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterSelectScreen (
    bibleId: String,
    bookId: String,
    viewModel: ChapterSelectViewModel,
    onChapterSelected: (String, String) -> Unit
){
    LaunchedEffect(key1 = bibleId, key2 = bookId) {
        viewModel.fetchBook(bibleId, bookId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceTint,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text("Viewing: ${ if (viewModel.bookUiState is BookUiState.Success) (viewModel.bookUiState as BookUiState.Success).result.bookSummary.name else "" }") }
            )
        },
    ) { paddingValues -> when (viewModel.bookUiState) {
            is BookUiState.Loading -> LoadingView(paddingValues)
            is BookUiState.Success -> ResultView(
                bibleId,
                (viewModel.bookUiState as BookUiState.Success).result,
                onChapterSelected,
                paddingValues)
            is BookUiState.Error -> ErrorView(paddingValues)
        }
    }
}

@Composable
fun ResultView(
    bibleId: String,
    book: Book,
    onChapterSelected: (String, String) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text("SELECT A CHAPTER:", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(book.chapters) {
                Card(
                    modifier = Modifier.padding(4.dp).clickable { onChapterSelected(bibleId, it.id) },
                ) {
                    Text(
                        text = it.number,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }
    }}