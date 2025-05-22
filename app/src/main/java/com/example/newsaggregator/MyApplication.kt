package com.example.newsaggregator

import android.app.Application
import org.koin.core.context.startKoin
import com.example.newsaggregator.di.appModule
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}