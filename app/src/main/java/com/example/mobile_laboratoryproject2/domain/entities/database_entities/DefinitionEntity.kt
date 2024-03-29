package com.example.mobile_laboratoryproject2.domain.entities.database_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "definitions",
    foreignKeys = [
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("wordId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val definition: String,
    val example: String?,
    @ColumnInfo(index = true)
    val wordId: Int?
)
