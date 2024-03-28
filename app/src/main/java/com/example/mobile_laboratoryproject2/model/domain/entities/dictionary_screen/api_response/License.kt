package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class License(
    val name: String,
    val url: String
)
