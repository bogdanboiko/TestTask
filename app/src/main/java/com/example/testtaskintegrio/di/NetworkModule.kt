package com.example.testtaskintegrio.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val networkModule = module {
    single {
        Firebase.firestore
    }
}