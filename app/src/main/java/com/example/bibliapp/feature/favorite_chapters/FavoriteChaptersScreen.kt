package com.example.bibliapp.feature.favorite_chapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO: amikor ide navigálunk, le kell kérni a kedvenc fejezetket a viewModeltől, továbbítani a kéréseket és reagálni a változásokra!
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteChaptersScreen (
    viewModel: FavoriteChaptersViewModel,
    onChapterSelected: (Long) -> Unit
){
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
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).fillMaxHeight()) {
            items(viewModel.getFavoriteChapters()) {
                Card(
                    modifier = Modifier.padding(4.dp).clickable { onChapterSelected(it.chapterId) },
                ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                ) {
                    Text(it.title)
                    Spacer(modifier = Modifier.fillMaxSize(0.7f))
                    // TODO: itt lehetne egy Checkbox(), hogy egyszerre többet is törölhessünk. Akkor az alsó Spacer kikommentezését is töröljük!
                    // Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "Delete"
                    )
                }
            }
            }
        }
    }
}