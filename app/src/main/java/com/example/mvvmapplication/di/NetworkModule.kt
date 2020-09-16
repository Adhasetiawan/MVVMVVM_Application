package com.example.mvvmapplication.di

import com.example.mvvmapplication.data.remote.apiClient
import org.koin.dsl.module

val networkModule = module {
    single { apiClient.provideOkHttpClient(get(),get(), get()) }
    single { apiClient.provideApiClient(get()) }
    single { apiClient.CacheInterceptor(get()) }
    single { apiClient.hasNetwork(get()) }
}