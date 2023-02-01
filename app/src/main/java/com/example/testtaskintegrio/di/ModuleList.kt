package com.example.testtaskintegrio.di

import org.koin.core.module.Module

val moduleList: List<Module> =
    listOf(appModule, networkModule, datasourceModule, repositoryModule, viewModelModule)