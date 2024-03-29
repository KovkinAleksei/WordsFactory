package com.example.mobile_laboratoryproject2.domain.entities.database_entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (
    tableName = "words"
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val word: String,
    val phonetics: String?,
    val audio: String,
    val partOfSpeech: String,
    val audioSource: String?
)
