package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen

import kotlinx.serialization.Serializable

@Serializable
data class Phonetics(
    val text: String,
    val audio: String,
    val sourceUrl: String? = null,
    val license: License? = null
)
