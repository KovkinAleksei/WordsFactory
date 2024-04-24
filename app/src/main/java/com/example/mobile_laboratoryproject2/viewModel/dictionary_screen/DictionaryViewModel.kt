package com.example.mobile_laboratoryproject2.viewModel.dictionary_screen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen.DictionaryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        // Валидация введённого для поиска слова
        validateSerchTextField()

        if (!_uiState.value.isWordCorrect)
            return

        // Поиск введённого слова
        viewModelScope.launch(Dispatchers.Default) {
            val word = dictionaryUseCase.searchWord(searchText.value.text)

            // Проверка нахождения слова
            _uiState.update { currentState ->
                if (word != null){
                    currentState.copy(
                        isInDictionary = dictionaryUseCase.isInDictionary(word),
                        word = word
                    )
                }
                else {
                    currentState.copy(
                        isWordCorrect = false,
                        errorMessage = R.string.word_not_found
                    )
                }
            }
        }
    }

    // Валидация поля поиска слова
    private fun validateSerchTextField() {
        val validationResult = dictionaryUseCase.validateSearchTextField(searchText.value.text)

        _uiState.update { currentState ->
            currentState.copy(
                isWordCorrect = validationResult.isCorrect,
                errorMessage = validationResult.errorMessage
            )
        }
    }

    // Удаление слова из словаря
    fun onRemoveButtonClick() {
        viewModelScope.launch(Dispatchers.Default) {
            if (_uiState.value.word != null)
                dictionaryUseCase.removeWordFromDictionary(_uiState.value.word!!.word)
        }

        _uiState.update { currentState ->
            currentState.copy(
                isInDictionary = false
            )
        }
    }

    // Добавление слова в словарь
    fun onAddButtonClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.word != null)
                dictionaryUseCase.addToDictionary(_uiState.value.word!!)
        }

        _uiState.update { currentState ->
            currentState.copy(
                isInDictionary = true
            )
        }
    }

    // Воспроизведение аудио
    fun onAudioClick(word: WordModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dictionaryUseCase.playAudio(word)
        }
    }

    // Закрытие диалога с ошибкой
    fun onDismiss() {
        _uiState.update {currentState ->
            currentState.copy(
                isWordCorrect = true,
                errorMessage = R.string.ok
            )
        }
    }
}