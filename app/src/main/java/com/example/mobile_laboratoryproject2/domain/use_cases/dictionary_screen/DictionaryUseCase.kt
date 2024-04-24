package com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.domain.use_cases.widget.IWidgetRepository
import com.example.mobile_laboratoryproject2.view.widget.WordsFactoryWidget
import com.example.mobile_laboratoryproject2.viewModel.ValidationResult
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel

class DictionaryUseCase(
    private val dictionaryRepository: IDictionaryRepository,
    private val widgetRepository: IWidgetRepository,
    private val context: Context
) {
    // Валидация поискового запроса
    fun validateSearchTextField(word: String): ValidationResult {
        return if (word.trim().isEmpty())
            ValidationResult(false, R.string.empty_search)
        else
            ValidationResult(true, R.string.ok)
    }

    // Сохранение слова локально
    suspend fun addToDictionary(word: WordModel) {
        if (isInDictionary(word))
            return

        dictionaryRepository.addToDictionary(word)

        // Обновление виджета
        updateWidget()
    }

    // Проверка повторного добавления слова в словарь
    suspend fun isInDictionary(word: WordModel): Boolean {
        return dictionaryRepository.getWordId(word.word) != null
    }

    // Получение значений слова
    suspend fun searchWord(word: String): WordModel? {
        return dictionaryRepository.searchWord(word)
    }

    // Удаление слова из словаря
    suspend fun removeWordFromDictionary(word: String) {
        dictionaryRepository.removeWord(word)

        // Обновление виджета
        updateWidget()
    }

    // Обновление виджета
    private suspend fun updateWidget() {
        try {
            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(WordsFactoryWidget::class.java)
                    .firstOrNull()
                    ?: return

            updateAppWidgetState(context = context, glanceId = glanceId) {
                it[intPreferencesKey("words_amount")] = widgetRepository.getWordsAmount()
            }

            val glanceAppWidget: GlanceAppWidget = WordsFactoryWidget()
            glanceAppWidget.update(context, glanceId)
        } catch (e: IllegalArgumentException) { }
    }
}