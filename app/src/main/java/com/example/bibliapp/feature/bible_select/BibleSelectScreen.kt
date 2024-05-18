package com.example.bibliapp.feature.bible_select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bibliapp.domain.BibleSummary
import com.example.bibliapp.ui.common.ErrorView
import com.example.bibliapp.ui.common.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleSelectScreen (
    viewModel: BibleSelectViewModel,
    onBibleSelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Select a Bible") }
            )
        },
    ) { paddingValues -> when (viewModel.bibleListUiState) {
            is BibleListUiState.Loading -> LoadingView(paddingValues)
            is BibleListUiState.Success -> ResultView(
                (viewModel.bibleListUiState as BibleListUiState.Success).result,
                onBibleSelected,
                paddingValues)
            is BibleListUiState.Error -> ErrorView(paddingValues)
        }
    }
}

@Composable
fun ResultView(
    bibles: List<BibleSummary>,
    onBibleSelected: (String) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(bibles) {
            TextButton(
                onClick = { onBibleSelected(it.id) }
            ) {
                Text("${it.abbreviationLocal} ${it.nameLocal} - ${it.descriptionLocal}")
            }
        }
    }
}