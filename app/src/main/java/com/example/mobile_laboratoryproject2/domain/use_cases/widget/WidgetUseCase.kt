package com.example.mobile_laboratoryproject2.domain.use_cases.widget

class WidgetUseCase(
    private val widgetRepository: IWidgetRepository
) {
    // Получение кол-ва слов в словаре
    suspend fun getWordsAmount(): Int {
        return widgetRepository.getWordsAmount()
    }

    // Получение кол-ва выученных слов
    suspend fun getLearnedWords(): Int {
        return widgetRepository.getLearnedWords()
    }
}