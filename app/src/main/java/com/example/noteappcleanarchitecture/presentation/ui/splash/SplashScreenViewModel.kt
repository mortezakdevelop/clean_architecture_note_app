package com.example.noteappcleanarchitecture.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {

    private val _events = MutableSharedFlow<Unit>(replay = 0)
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(4000)
            _events.emit(Unit)
        }
    }
}