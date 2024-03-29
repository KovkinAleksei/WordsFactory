package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.local_data_source.AppDatabase
import com.example.mobile_laboratoryproject2.data.local_data_source.UserDao
import com.example.mobile_laboratoryproject2.data.local_data_source.WordDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        AppDatabase.createDatabase(get())
    }

    single<UserDao> {
        val database = get<AppDatabase>()
        database.userDao
    }

    factory<WordDao> {
        val database = get<AppDatabase>()
        database.wordDao
    }
}