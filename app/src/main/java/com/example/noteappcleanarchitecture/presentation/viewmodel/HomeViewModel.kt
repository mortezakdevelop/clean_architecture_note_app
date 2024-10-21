package com.example.noteappcleanarchitecture.presentation.viewmodel

import com.example.noteappcleanarchitecture.data.entity.NoteEntity
import com.example.noteappcleanarchitecture.data.utils.DataStatus
import com.example.noteappcleanarchitecture.domain.usecase.AllNoteUseCase
import com.example.noteappcleanarchitecture.domain.usecase.DeleteUseCase
import com.example.noteappcleanarchitecture.domain.usecase.SearchUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allNoteUseCase: AllNoteUseCase,
    private val searchUseCase: SearchUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {

    private val _getAllNotes : MutableStateFlow<DataStatus<List<NoteEntity>>?> = MutableStateFlow(null)
    val getAllNotes = _getAllNotes.asStateFlow()

    private val _searchNotes : MutableStateFlow<DataStatus<List<NoteEntity>>?> = MutableStateFlow(null)
    val searchNotes:StateFlow<DataStatus<List<NoteEntity>>?> = _searchNotes

    fun getAll() = viewModelScope.launch {
        allNoteUseCase.getAllNote().collect{
            _getAllNotes.value = DataStatus.success(it,it.isEmpty())
        }
    }

    fun searchNote(search : String) = viewModelScope.launch {
        searchUseCase.searchNote(search).collect{ note ->
            _searchNotes.update { DataStatus.success(note,note.isEmpty()) }
        }
    }

    fun deleteNote(entity: NoteEntity) = viewModelScope.launch {
        deleteUseCase.deleteNote(entity)
    }

}