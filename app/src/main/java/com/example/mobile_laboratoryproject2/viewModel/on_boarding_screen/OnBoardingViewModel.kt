package com.example.mobile_laboratoryproject2.viewModel.on_boarding_screen

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.UserPreferences
import com.example.mobile_laboratoryproject2.domain.use_cases.sign_up_screen.UserPreferencesSerializer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val application: Application
): AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(OnBoardingUiState())
    val uiState = _uiState.asStateFlow()

    // Пролистывание страниц нажатием кнопки Next
    fun onNextClick()
    {
        _uiState.update { currentState ->
            currentState.copy(currentPage = currentState.currentPage + 1)
        }
    }

    // Пролистывание страниц свайпом
    fun onPagerScroll(selectedPage: Int)
    {
        _uiState.update { currentState ->
            currentState.copy(currentPage = selectedPage)
        }
    }
}