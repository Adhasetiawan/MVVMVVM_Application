package com.example.mvvmapplication.di

import com.example.mvvmapplication.data.lokal.AppDatabase
import com.example.mvvmapplication.data.lokal.dao.MovieDao
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
    single { provideMovieDao(get()) }
}

fun provideMovieDao (database: AppDatabase) : MovieDao = database.MovieDao()