package com.example.testtaskintegrio.di

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.PointPagingSource
import com.example.testtaskintegrio.data.remote.RemoteDatasourceImpl
import com.example.testtaskintegrio.data.repository.PlaceRepositoryImpl
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val datasourceModule = module {
    single<PlaceDatasource.Remote> {
        RemoteDatasourceImpl(get())
    }

    single {
        Firebase.firestore.collection("points").orderBy("coordinates")
    }

    single {
        PointPagingSource(get())
    }
}

val repositoryModule = module {
    single<PlaceRepository> {
        PlaceRepositoryImpl(get(), get())
    }
}