package com.example.testtaskintegrio.di

import com.example.testtaskintegrio.TestApplication
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single {
        androidApplication() as TestApplication
    }
}