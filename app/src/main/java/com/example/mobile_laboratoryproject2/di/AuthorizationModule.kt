package com.example.mobile_laboratoryproject2.di

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mobile_laboratoryproject2.TestPreferences
import com.example.mobile_laboratoryproject2.viewModel.question_screen.TestPreferencesSerializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val authorizationModule = module {
    single<FirebaseAuth> {
        Firebase.auth
    }
}