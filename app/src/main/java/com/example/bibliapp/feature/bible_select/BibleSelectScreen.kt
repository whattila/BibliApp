package com.example.bibliapp.feature.bible_select

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            is BibleListUiState.Success -> BiblesByLanguagesView(
                (viewModel.bibleListUiState as BibleListUiState.Success).result,
                onBibleSelected,
                paddingValues)
            is BibleListUiState.Error -> ErrorView(paddingValues)
        }
    }
}

@Composable
fun BiblesByLanguagesView(
    sections: List<SectionData>,
    onBibleSelected: (String) -> Unit,
    paddingValues: PaddingValues
) {
    val isExpandedMap = rememberSavableSnapshotStateMap {
        List(sections.size) { index: Int -> index to true }
            .toMutableStateMap()
    }

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        content = {
            sections.onEachIndexed { index, sectionData ->
                Section(
                    sectionData = sectionData,
                    isExpanded = isExpandedMap[index] ?: true,
                    onLanguageSelected = {
                        isExpandedMap[index] = !(isExpandedMap[index] ?: true)
                    },
                    onBibleSelected = onBibleSelected
                )
            }
        }
    )
}

fun LazyListScope.Section(
    sectionData: SectionData,
    isExpanded: Boolean,
    onLanguageSelected: () -> Unit,
    onBibleSelected: (String) -> Unit
) {
    item {
        SectionHeader(
            text = sectionData.language.name,
            isExpanded = isExpanded,
            onSelected = onLanguageSelected)
    }
    if (isExpanded) {
        items(sectionData.bibles) {
            SectionItem(
                bibleSummary = it,
                onSelected = onBibleSelected)
        }
    }
}

@Composable
fun SectionHeader(
    text: String,
    isExpanded: Boolean,
    onSelected: () -> Unit
) {
    Row(modifier = Modifier
        .clickable { onSelected() }
        .background(Color.LightGray)
        .padding(vertical = 8.dp, horizontal = 16.dp)) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1.0f)
        )
        if (isExpanded) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Collapse"
            )
        } else {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand"
            )
        }
    }
}

@Composable
fun SectionItem(
    bibleSummary: BibleSummary,
    onSelected: (String) -> Unit
) {
    TextButton(
        onClick = { onSelected(bibleSummary.id) }
    ) {
        Text("${bibleSummary.abbreviationLocal} ${bibleSummary.nameLocal} - ${bibleSummary.descriptionLocal}")
    }
}

fun <K, V> snapshotStateMapSaver() = Saver<SnapshotStateMap<K, V>, Any>(
    save = { state -> state.toList() },
    restore = { value ->
        @Suppress("UNCHECKED_CAST")
        (value as? List<Pair<K, V>>)?.toMutableStateMap() ?: mutableStateMapOf<K, V>()
    }
)
@Composable
fun <K, V> rememberSavableSnapshotStateMap(init: () -> SnapshotStateMap<K, V>): SnapshotStateMap<K, V> =
    rememberSaveable(saver = snapshotStateMapSaver(), init = init)