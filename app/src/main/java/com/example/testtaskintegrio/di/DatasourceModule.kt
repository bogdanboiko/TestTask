package com.example.testtaskintegrio.di

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.RemoteDatasourceImpl
import com.example.testtaskintegrio.data.repository.PlaceRepositoryImpl
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import org.koin.dsl.module

val datasourceModule = module {
    single<PlaceDatasource.Remote> {
        RemoteDatasourceImpl(get())
    }
}

val repositoryModule = module {
    single<PlaceRepository> {
        PlaceRepositoryImpl(get())
    }
}