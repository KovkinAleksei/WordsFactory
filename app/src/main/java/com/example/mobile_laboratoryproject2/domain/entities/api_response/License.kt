package com.example.mobile_laboratoryproject2.domain.entities.api_response

import kotlinx.serialization.Serializable

@Serializable
data class License(
    val name: String,
    val url: String
)
