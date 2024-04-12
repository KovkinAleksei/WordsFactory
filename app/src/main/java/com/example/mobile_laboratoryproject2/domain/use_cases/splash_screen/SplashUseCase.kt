package com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen

import com.example.mobile_laboratoryproject2.UserPreferences
import kotlinx.coroutines.flow.Flow

class SplashUseCase(
    private val splashRepository: ISplashRepository
) {
    suspend fun IsOnBoardingShown(): Flow<UserPreferences> {
        return splashRepository.IsOnBoardingShown()
    }

    suspend fun UpdateOnBoarding() {
        splashRepository.UpdateOnBoarding()
    }
}