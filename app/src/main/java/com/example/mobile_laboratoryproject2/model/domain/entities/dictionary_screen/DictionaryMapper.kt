package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response.Definition
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response.DictionaryRecord
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.WordEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.usable_models.DefinitionModel
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.usable_models.WordModel

class DictionaryMapper {
    companion object  {
        // Перевод полученого от сервера ответа в модель, используемую в представлении
        fun dictionaryRecordToWordModel(dictionaryRecord: DictionaryRecord): WordModel {
            return WordModel(
                word = dictionaryRecord.word.replaceFirstChar { it.uppercase() },
                phonetics = dictionaryRecord.phonetic,
                audio = if (dictionaryRecord.phonetics.isNotEmpty())
                    dictionaryRecord.phonetics[0].audio
                else
                    "",
                partOfSpeech = dictionaryRecord.meanings[0].partOfSpeech.replaceFirstChar { it.uppercase() },
                audioSource = if (dictionaryRecord.phonetics.isNotEmpty())
                    dictionaryRecord.phonetics[0].sourceUrl
                else
                    "",
                definitions = getDefinitionModels(dictionaryRecord)
            )
        }

        // Получение переведённого списка определений слова
        private fun getDefinitionModels(dictionaryRecord: DictionaryRecord): List<DefinitionModel> {
            val definitions = mutableListOf<Definition>()

            dictionaryRecord.meanings.forEach {
                definitions.addAll(it.definitions)
            }

            return definitions.map {
                DefinitionModel(
                    definition = it.definition,
                    example = it.example
                )
            }
        }

        // Получение сущности слова для хранения в бд
        fun wordModelToWordEntity(word: WordModel): WordEntity {
            return WordEntity(
                word = word.word,
                phonetics = word.phonetics,
                audio = word.audio,
                partOfSpeech = word.partOfSpeech,
                audioSource = word.audioSource
            )
        }

        // Получение сущностей определения слова для хранения в бд
        fun wordModelToDefinitionEntities(word: WordModel, wordEntityId: Int?): List<DefinitionEntity> {
            return word.definitions.map {
                DefinitionEntity(
                    definition = it.definition,
                    example = it.example,
                    wordId = wordEntityId
                )
            }
        }

        fun wordEntityToWordModel(word: WordEntity, definitions: List<DefinitionEntity>): WordModel {
            return WordModel(
                word = word.word.replaceFirstChar { it.uppercase() },
                phonetics = word.phonetics,
                audio = word.audio,
                partOfSpeech = word.partOfSpeech,
                audioSource = word.audioSource,
                definitions = definitionEntitiesToDefinitionModels(definitions)
            )
        }

        private fun definitionEntitiesToDefinitionModels(definitions: List<DefinitionEntity>): List<DefinitionModel> {
            return definitions.map {
                DefinitionModel(
                    definition = it.definition,
                    example = it.example
                )
            }
        }
    }
}