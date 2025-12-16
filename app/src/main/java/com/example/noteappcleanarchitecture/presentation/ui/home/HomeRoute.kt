package com.example.noteappcleanarchitecture.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteappcleanarchitecture.data.entity.NoteEntity
import com.example.noteappcleanarchitecture.presentation.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onAddNote: () -> Unit,
    onEditNote: (Int) -> Unit,
    onDeleteNote: (NoteEntity) -> Unit
){
    val result by viewModel.getAllNotes.collectAsStateWithLifecycle(initialValue = null)

    val note = result?.data ?: emptyList()
    val isEmpty = note.isEmpty()

    HomeScreen(
        notes = note,
        isEmpty = isEmpty,
        onAddNote = onAddNote,
        onEditNote = onEditNote,
        onDeleteNote = onDeleteNote
    )
}