package com.example.noteappcleanarchitecture.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteappcleanarchitecture.data.entity.NoteEntity

@Composable
fun NotesGridScreen(
    notes: List<NoteEntity>,
    modifier: Modifier = Modifier,
    onEditNote: (Int) -> Unit,
    onDeleteNote: (NoteEntity) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(notes, key = { it.id }) { note ->
            NoteCardScreen(
                note = note,
                onEdit = { onEditNote(note.id) },
                onDelete = { onDeleteNote(note) }
            )
        }
    }
}