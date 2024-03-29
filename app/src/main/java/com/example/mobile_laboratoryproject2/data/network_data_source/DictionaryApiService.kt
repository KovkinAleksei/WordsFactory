package com.example.mobile_laboratoryproject2.data.network_data_source

import com.example.mobile_laboratoryproject2.domain.entities.api_response.DictionaryRecord
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("{word}")
    suspend fun getDictionaryRecord(@Path("word") word: String) : List<DictionaryRecord>
}