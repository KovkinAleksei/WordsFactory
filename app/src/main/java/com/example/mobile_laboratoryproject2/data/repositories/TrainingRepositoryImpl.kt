package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.TrainingDao
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.ITrainingRepository

class TrainingRepositoryImpl(
    private val trainingDao: TrainingDao
) : ITrainingRepository {
    // Получение кол-ва слов в словаре
    override suspend fun getWordsAmount(): Int {
        return trainingDao.getWordsAmount()
    }
}