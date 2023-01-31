package com.example.testtaskintegrio.di

import com.example.testtaskintegrio.PlaceViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        PlaceViewModel(get())
    }
}