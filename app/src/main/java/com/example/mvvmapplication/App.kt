package com.example.mvvmapplication

import android.app.Application
import com.example.mvvmapplication.di.appModule
import com.example.mvvmapplication.di.databaseModule
import com.example.mvvmapplication.di.networkModule
import com.example.mvvmapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin(this, listOf(
//            networkModule,
//            databaseModule,
//            appModule,
//            viewModelModule))

        startKoin {
            androidContext(this@App)
            modules(networkModule, databaseModule, appModule, viewModelModule)
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
}
}