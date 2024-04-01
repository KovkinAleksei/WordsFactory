package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.WordDao
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.ITrainingRepository

class TrainingRepositoryImpl(
    private val wordDao: WordDao
): ITrainingRepository {
    // Получение кол-ва слов в словаре
    override suspend fun getWordsAmount(): Int {
        return wordDao.getWordsAmount()
    }
}