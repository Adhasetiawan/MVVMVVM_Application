package com.example.mvvmapplication.di

import com.example.mvvmapplication.data.pref.SharedPreference
import com.example.mvvmapplication.data.respository.MovieRespository
import com.example.mvvmapplication.util.NetworkHelper
import com.example.mvvmapplication.util.scheduler.AppSchedulerProvider
import com.example.mvvmapplication.util.scheduler.SchedulerProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.dsl.module

val appModule = module {
    factory { GroupAdapter<ViewHolder>() }
    single<SchedulerProvider> { AppSchedulerProvider() }
    single { NetworkHelper(get()) }
    single {SharedPreference(get())}
    single {MovieRespository(get(), get())}
}