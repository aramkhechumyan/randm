package com.task.rickandmorty.presentation

import android.app.Application

import com.task.rickandmorty.data.networck.retrofit.networkModule
import com.task.rickandmorty.presentation.main.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()
            androidContext(androidContext = this@App)

            modules(
                viewModelModule,
                networkModule
            )
        }
    }
}
