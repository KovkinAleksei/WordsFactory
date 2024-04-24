package com.example.mobile_laboratoryproject2.viewModel.widget

import com.example.mobile_laboratoryproject2.domain.use_cases.widget.WidgetUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WidgetViewModel: KoinComponent {
    private val widgetUseCase: WidgetUseCase by inject<WidgetUseCase>()
    var wordsAmount = 0
    var learnedWords = 0

    suspend fun updateData() {
        wordsAmount = getWordsAmount()
        learnedWords = getLearnedWords()
    }

    private suspend fun getWordsAmount(): Int {
        return widgetUseCase.getWordsAmount()
    }

    private suspend fun getLearnedWords(): Int {
        return widgetUseCase.getLearnedWords()
    }
}