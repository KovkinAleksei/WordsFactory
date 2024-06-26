package com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen

import com.example.mobile_laboratoryproject2.UserPreferences
import kotlinx.coroutines.flow.Flow

interface ISplashRepository {
    suspend fun isOnBoardingShown(): Flow<UserPreferences>
    suspend fun updateOnBoarding()
}