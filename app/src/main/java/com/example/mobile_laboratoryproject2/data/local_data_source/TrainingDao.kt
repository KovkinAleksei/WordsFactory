package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TrainingDao {
    // Получение кол-ва слов в словаре
    @Query("SELECT COUNT(*) FROM words")
    suspend fun getWordsAmount(): Int
}