package com.example.noteappcleanarchitecture.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTopBar(
    title: String,
    query: String,
    isSearching: Boolean,
    onSearchClick: () -> Unit,
    onCloseSearch: () -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (!isSearching) {
                // عنوان وسط مثل XML
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp
                    )
                }
            } else {
                // حالت سرچ: TextField جای عنوان میاد
                TextField(
                    value = query,
                    onValueChange = onQueryChange,
                    singleLine = true,
                    placeholder = { Text("Search") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        actions = {
            if (!isSearching) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            } else {
                IconButton(onClick = onCloseSearch) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close search"
                    )
                }
            }
        }
    )
}