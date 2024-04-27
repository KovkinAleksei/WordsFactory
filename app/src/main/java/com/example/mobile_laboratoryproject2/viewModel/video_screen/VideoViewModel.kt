package com.example.mobile_laboratoryproject2.viewModel.video_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VideoUiState())
    val uiState = _uiState.asStateFlow()

    // Закрытие диалога с ошибкой
    fun onDismiss() {
        _uiState.update { currentState ->
            currentState.copy(isClosed = true)
        }
    }
}