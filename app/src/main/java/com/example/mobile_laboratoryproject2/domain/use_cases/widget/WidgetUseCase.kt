package com.example.mobile_laboratoryproject2.domain.use_cases.widget

class WidgetUseCase(
    private val widgetRepository: IWidgetRepository
) {
    suspend fun getWordsAmount(): Int {
        return widgetRepository.getWordsAmount()
    }

    suspend fun getLearnedWords(): Int {
        return widgetRepository.getLearnedWords()
    }
}