package com.example.mvvmapplication.di

import com.example.mvvmapplication.ui.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}