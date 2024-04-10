package com.example.mobile_laboratoryproject2.viewModel.video_screen

import androidx.lifecycle.ViewModel
import com.example.mobile_laboratoryproject2.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VideoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VideoUiState())
    val uiState = _uiState.asStateFlow()
}