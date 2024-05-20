package com.example.bibliapp.feature.favorite_chapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bibliapp.domain.ChapterHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteChaptersScreen (
    viewModel: FavoriteChaptersViewModel,
    onChapterSelected: (Long) -> Unit
){
    val chapterList by viewModel.favoriteChapters.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("My favorite chapters") }
            )
        },
    ) { paddingValues ->
        if (chapterList.isEmpty()) {
            EmptyListView(paddingValues = paddingValues)
        }
        else {
            FavoriteChaptersListView(
                viewModel = viewModel,
                chapters = chapterList,
                paddingValues = paddingValues,
                onChapterSelected = onChapterSelected)
        }
    }
}

@Composable
fun EmptyListView(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "You don't have any favorites", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun FavoriteChaptersListView(
    viewModel: FavoriteChaptersViewModel,
    chapters: List<ChapterHeader>,
    paddingValues: PaddingValues,
    onChapterSelected: (Long) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxHeight()) {
        items(chapters) {
            Card(
                modifier = Modifier.padding(4.dp).clickable { onChapterSelected(it.id) },
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                ) {
                    Text(it.reference)
                    Spacer(modifier = Modifier.fillMaxSize(0.7f))
                    // TODO: itt lehetne egy Checkbox(), hogy egyszerre többet is törölhessünk. Akkor az alsó Spacer kikommentezését is töröljük!
                    // Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = { coroutineScope.launch {
                                viewModel.removeChapterByHeader(it)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                    }
                }
            }
        }
    }
}