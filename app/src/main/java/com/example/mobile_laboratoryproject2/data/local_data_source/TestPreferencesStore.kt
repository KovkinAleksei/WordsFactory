package com.example.mobile_laboratoryproject2.data.local_data_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mobile_laboratoryproject2.TestPreferences
import com.example.mobile_laboratoryproject2.viewModel.question_screen.TestPreferencesSerializer

class TestPreferencesStore(
    private val context: Context
) {
    companion object {
        private val Context.testPreferencesStore: DataStore<TestPreferences> by dataStore(
            fileName = "test_preferences",
            serializer = TestPreferencesSerializer
        )
    }

    val store: DataStore<TestPreferences>
        get() = context.testPreferencesStore
}