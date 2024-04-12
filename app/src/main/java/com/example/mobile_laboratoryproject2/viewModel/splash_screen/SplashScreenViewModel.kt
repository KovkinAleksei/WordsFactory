package com.example.mobile_laboratoryproject2.viewModel.splash_screen

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.UserPreferences
import com.example.mobile_laboratoryproject2.domain.use_cases.sign_up_screen.UserPreferencesSerializer
import com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen.SplashUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val splashUseCase: SplashUseCase,
    private val application: Application
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(SplashScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = "user_preferences",
        serializer = UserPreferencesSerializer
    )

    private val userPreferencesStore: DataStore<UserPreferences>
        get() = getApplication<Application>().applicationContext.userPreferencesStore

    init {
        viewModelScope.launch {
            val onBoardingShowFlow = splashUseCase.IsOnBoardingShown()
            var isCollected = false

            onBoardingShowFlow.collectLatest {
                if (!isCollected) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isOnBoardingShown = it.isOnBoardingShown,
                            isCompleted = true
                        )
                    }

                    isCollected = true
                }

                splashUseCase.UpdateOnBoarding()
            }

            splashUseCase.UpdateOnBoarding()
        }
    }
}
