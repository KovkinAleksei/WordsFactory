package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.model.data.database.AppDatabase
import com.example.mobile_laboratoryproject2.model.data.database.UserDao
import com.example.mobile_laboratoryproject2.model.data.database.WordDao
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