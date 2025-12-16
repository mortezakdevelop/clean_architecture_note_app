package com.example.noteappcleanarchitecture.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.noteappcleanarchitecture.data.entity.NoteEntity
import com.example.noteappcleanarchitecture.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    notes: List<NoteEntity>,
    isEmpty: Boolean,
    onAddNote: () -> Unit,
    onEditNote: (Int) -> Unit,
    onDeleteNote: (NoteEntity) -> Unit
) {

    var isSearching by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(

        topBar = {
            NotesTopBar(
                title = "My Note",
                query = query,
                isSearching = isSearching,
                onSearchClick = { isSearching = true },
                onCloseSearch = {
                    isSearching = false
                    query = ""
                    viewModel.searchNote("") // پاک کردن سرچ
                },
                onQueryChange = {
                    query = it
                    viewModel.searchNote(it) // مثل onQueryTextChange
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->

        if (isEmpty) {
            // جای emptyLay
            EmptyNotesContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        } else {
            // جای RecyclerView
            NotesGridScreen(
                notes = notes,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                onEditNote = onEditNote,
                onDeleteNote = onDeleteNote
            )
        }
    }
}