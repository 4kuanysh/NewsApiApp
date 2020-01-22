package com.example.newsapiapp

import android.app.Application
import com.example.newsapiapp.di.dbModule
import com.example.newsapiapp.di.repoModule
import com.example.newsapiapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(dbModule, repoModule, viewModelModule))
        }
    }

}