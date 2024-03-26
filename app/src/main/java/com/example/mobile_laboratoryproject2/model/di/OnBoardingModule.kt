package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.model.domain.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class OnBoardingModule {

    @Provides
    fun provideSignUpUseCase(): SignUpUseCase {
        return SignUpUseCase()
    }
}