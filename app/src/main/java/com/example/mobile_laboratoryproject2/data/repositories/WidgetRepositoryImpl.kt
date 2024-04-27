package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.WidgetDao
import com.example.mobile_laboratoryproject2.domain.use_cases.widget.IWidgetRepository

class WidgetRepositoryImpl(
    private val widgetDao: WidgetDao
) : IWidgetRepository {
    // Получение кол-ва выученных слов
    override suspend fun getLearnedWords(): Int {
        return widgetDao.getLearnedWords()
    }

    // Получение кол-ва слов в словаре
    override suspend fun getWordsAmount(): Int {
        return widgetDao.getWordsAmount()
    }
}