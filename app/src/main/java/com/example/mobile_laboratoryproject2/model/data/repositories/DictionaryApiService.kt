package com.example.mobile_laboratoryproject2.model.data.repositories

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.DictionaryRecord
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("{word}")
    suspend fun getDictionaryRecord(@Path("word") word: String) : List<DictionaryRecord>
}