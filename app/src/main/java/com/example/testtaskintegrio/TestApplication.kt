package com.example.testtaskintegrio

import android.app.Application
import com.example.testtaskintegrio.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TestApplication)
            modules(moduleList)
        }
    }
}