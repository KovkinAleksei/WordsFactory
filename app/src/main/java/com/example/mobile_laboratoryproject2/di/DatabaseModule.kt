package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.local_data_source.AppDatabase
import com.example.mobile_laboratoryproject2.data.local_data_source.DictionaryDao
import com.example.mobile_laboratoryproject2.data.local_data_source.QuestionDao
import com.example.mobile_laboratoryproject2.data.local_data_source.TrainingDao
import com.example.mobile_laboratoryproject2.data.local_data_source.WidgetDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        AppDatabase.createDatabase(get())
    }

    factory<DictionaryDao> {
        val database = get<AppDatabase>()
        database.dictionaryDao
    }

    factory<TrainingDao> {
        val database = get<AppDatabase>()
        database.trainingDao
    }

    factory<QuestionDao> {
        val database = get<AppDatabase>()
        database.questionDao
    }

    factory<WidgetDao> {
        val database = get<AppDatabase>()
        database.widgetDao
    }
}