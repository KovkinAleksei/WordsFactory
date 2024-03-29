package com.example.mobile_laboratoryproject2.domain.entities.api_response

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Definition(
    val definition: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String? = null
)
