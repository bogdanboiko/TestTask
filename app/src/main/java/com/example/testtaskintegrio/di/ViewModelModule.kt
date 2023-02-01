package com.example.testtaskintegrio.di

import com.example.testtaskintegrio.presenter.PlaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlaceViewModel(get()) }
}