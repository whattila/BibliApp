package com.example.bibliapp.feature.book_select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliapp.domain.Bible
import com.example.bibliapp.ui.common.ErrorView
import com.example.bibliapp.ui.common.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSelectScreen (
    bibleId: String,
    viewModel: BookSelectViewModel,
    onBookSelected: (String, String) -> Unit
){
    LaunchedEffect(key1 = bibleId) {
        viewModel.fetchBible(bibleId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceTint,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text("Viewing: ${ if (viewModel.bibleUiState is BibleUiState.Success) (viewModel.bibleUiState as BibleUiState.Success).result.summary.nameLocal else "" }") }
            )
        },
    ) { paddingValues -> when (viewModel.bibleUiState) {
            is BibleUiState.Loading -> LoadingView(paddingValues)
            is BibleUiState.Success -> ResultView(
                bibleId,
                (viewModel.bibleUiState as BibleUiState.Success).result,
                onBookSelected,
                paddingValues)
            is BibleUiState.Error -> ErrorView(paddingValues)
        }
    }
}

@Composable
fun ResultView(
    bibleId: String,
    bible: Bible,
    onBookSelected: (String, String) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text("SELECT A BOOK:", style = MaterialTheme.typography.headlineLarge)
        LazyColumn(modifier = Modifier.padding(
            top = 20.dp,
            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
            end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding())
        ) {
            items(bible.books) {
                TextButton(
                    onClick = { onBookSelected(bibleId, it.id) }
                ) {
                    Text(text = it.name, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}