package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WidgetDao {
    // Получение кол-во слов
    @Query("SELECT COUNT(*) FROM words")
    suspend fun getWordsAmount() : Int

    @Query("SELECT COUNT(*) FROM words WHERE learningCoefficient > 5")
    suspend fun getLearnedWords(): Int
}
