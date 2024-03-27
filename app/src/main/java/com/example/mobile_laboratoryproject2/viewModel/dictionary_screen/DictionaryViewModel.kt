package com.example.mobile_laboratoryproject2.viewModel.dictionary_screen

import android.net.http.HttpException
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen.DictionaryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val dictionaryUseCase: DictionaryUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DictionaryUiState())
    val uiState = _uiState.asStateFlow()

    val searchText = mutableStateOf(TextFieldValue(""))

    // Ввод текста в поле поиска
    fun handleSearchInput(input: TextFieldValue) {
        searchText.value = input
    }

    // Поиск
    fun onSearchButtonClick() {
        viewModelScope.launch(Dispatchers.Default) {
            try{
                val records = dictionaryUseCase.getDictionaryRecord(searchText.value.text)
            }
            catch (e: retrofit2.HttpException) {

            }
        }
    }
}