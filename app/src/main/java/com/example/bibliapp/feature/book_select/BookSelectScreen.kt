package com.example.bibliapp.feature.book_select

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSelectScreen (
    bibleId: String?,
    viewModel: BookSelectViewModel,
    onBookSelected: (String?, String) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Viewing: ${viewModel.getBible().summary.abbreviationLocal}") }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Select a book:", fontSize = 30.sp)
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(viewModel.getBible().books) {
                    TextButton(
                        onClick = { onBookSelected(bibleId, it.id) }
                    ) {
                        Text(it.name)
                    }
                }
            }
        }
    }
}