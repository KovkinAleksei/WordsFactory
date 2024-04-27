package com.example.mobile_laboratoryproject2.data.repositories

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mobile_laboratoryproject2.UserPreferences
import com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen.ISplashRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen.UserPreferencesSerializer
import kotlinx.coroutines.flow.Flow

class SplashRepositoryImpl(
    private val application: Application
) : ISplashRepository {
    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = "user_preferences",
        serializer = UserPreferencesSerializer
    )

    private val userPreferencesStore: DataStore<UserPreferences>
        get() = application.applicationContext.userPreferencesStore

    override suspend fun isOnBoardingShown(): Flow<UserPreferences> {
        return userPreferencesStore.data
    }

    override suspend fun updateOnBoarding() {
        userPreferencesStore.updateData {
            it.toBuilder().setIsOnBoardingShown(true).build()
        }
    }
}