package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.WordDao
import com.example.mobile_laboratoryproject2.data.network_data_source.DictionaryApiService
import com.example.mobile_laboratoryproject2.domain.entities.Mapper
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel
import com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen.IDictionaryRepository
import retrofit2.HttpException
import java.net.UnknownHostException

class DictionaryRepositoryImpl(
    private val dictionaryService: DictionaryApiService,
    private val wordDao: WordDao
) : IDictionaryRepository {
    // Поиск слова
    override suspend fun searchWord(word: String) : WordModel? {
        // Получение слова с сервера
        return try {
            getWordFromServer(word)
        }
        // Получение слова из бд
        catch (exception: UnknownHostException) {
            getWordFromDatabase(word)
        }
    }

    // Получение слова с сервера
    private suspend fun getWordFromServer(word: String): WordModel? {
        return try {
            val wordEntity = dictionaryService.getDictionaryRecord(word)[0]
            Mapper.dictionaryRecordToWordModel(wordEntity)
        }
        catch (exception: HttpException) {
            null
        }
    }

    // Получение слова из бд
    private suspend fun getWordFromDatabase(word: String): WordModel? {
        val wordEntity = wordDao.getWord(word) ?: return null
        val definitionEntities = getWordDefinitions(wordEntity.id!!)

        return Mapper.wordEntityToWordModel(wordEntity, definitionEntities)
    }

    // Получение определений слова из бд
    private suspend fun getWordDefinitions(wordId: Int): List<DefinitionEntity> {
        return wordDao.getWordDefinitions(wordId)
    }

    // Сохранение слова локально
    override suspend fun addToDictionary(word: WordModel) {
        // Сохранение слова
        val wordEntity = Mapper.wordModelToWordEntity(word)
        val formatedWord = wordEntity.copy(word = wordEntity.word.lowercase())

        wordDao.addWord(formatedWord)

        // Сохранение определений слова
        addDefinitionsToDictionary(word)
    }

    // Сохранение определений слова локально
    private suspend fun addDefinitionsToDictionary(word: WordModel) {
        val wordId = getWordId(word.word)
        val definitionEntities = Mapper.wordModelToDefinitionEntities(word, wordId)

        wordDao.addDefinitions(definitionEntities)
    }

    // Получение id слова
    override suspend fun getWordId(word: String) : Int? {
        val formatedWord = word.lowercase()

        return wordDao.getWordId(formatedWord)
    }

    // Удаление слова из бд
    override suspend fun removeWord(word: String) {
        val formatedWord = word.lowercase()

        wordDao.removeWord(formatedWord)
    }
}