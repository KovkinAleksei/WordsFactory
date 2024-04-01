package com.example.mobile_laboratoryproject2.domain.use_cases.training_screen

class TrainingUseCase(
    private val trainingRepository: ITrainingRepository
) {
    // Получение кол-ва слов в словаре
    suspend fun getWordsAmount(): Int {
        return trainingRepository.getWordsAmount()
    }
}