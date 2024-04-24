package com.example.mobile_laboratoryproject2.viewModel.widget

import com.example.mobile_laboratoryproject2.domain.use_cases.widget.WidgetUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WidgetViewModel: KoinComponent {
    private val widgetUseCase: WidgetUseCase by inject<WidgetUseCase>()
    var wordsAmount = 0
    var learnedWords = 0

    // Обновление виджета
    suspend fun updateData() {
        wordsAmount = getWordsAmount()
        learnedWords = getLearnedWords()
    }

    // Получение кол-ва слов в словаре
    private suspend fun getWordsAmount(): Int {
        return widgetUseCase.getWordsAmount()
    }

    // Получение кол-ва выученных слов
    private suspend fun getLearnedWords(): Int {
        return widgetUseCase.getLearnedWords()
    }
}