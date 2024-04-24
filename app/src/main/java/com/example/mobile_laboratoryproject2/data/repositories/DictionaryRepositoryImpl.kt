package com.example.mobile_laboratoryproject2.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mobile_laboratoryproject2.data.local_data_source.DictionaryDao
import com.example.mobile_laboratoryproject2.data.network_data_source.DictionaryApiService
import com.example.mobile_laboratoryproject2.domain.entities.Mapper
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel
import com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen.IDictionaryRepository
import com.example.mobile_laboratoryproject2.view.widget.WordsFactoryWidget
import retrofit2.HttpException
import java.net.UnknownHostException

class DictionaryRepositoryImpl(
    private val dictionaryService: DictionaryApiService,
    private val dictionaryDao: DictionaryDao
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
        val wordEntity = dictionaryDao.getWord(word) ?: return null
        val definitionEntities = getWordDefinitions(wordEntity.id!!)

        return Mapper.wordEntityToWordModel(wordEntity, definitionEntities)
    }

    // Получение определений слова из бд
    private suspend fun getWordDefinitions(wordId: Int): List<DefinitionEntity> {
        return dictionaryDao.getWordDefinitions(wordId)
    }

    // Сохранение слова локально
    override suspend fun addToDictionary(word: WordModel) {
        // Сохранение слова
        val wordEntity = Mapper.wordModelToWordEntity(word)
        val formatedWord = wordEntity.copy(word = wordEntity.word.lowercase())

        dictionaryDao.addWord(formatedWord)

        // Сохранение определений слова
        addDefinitionsToDictionary(word)

        // Обновление Preferences

    }

    // Сохранение определений слова локально
    private suspend fun addDefinitionsToDictionary(word: WordModel) {
        val wordId = getWordId(word.word)
        val definitionEntities = Mapper.wordModelToDefinitionEntities(word, wordId)

        dictionaryDao.addDefinitions(definitionEntities)
    }

    // Получение id слова
    override suspend fun getWordId(word: String) : Int? {
        val formatedWord = word.lowercase()

        return dictionaryDao.getWordId(formatedWord)
    }

    // Удаление слова из бд
    override suspend fun removeWord(word: String) {
        val formatedWord = word.lowercase()

        dictionaryDao.removeWord(formatedWord)
    }
}