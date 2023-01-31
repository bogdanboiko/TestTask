package com.example.testtaskintegrio.presenter.model

import com.google.firebase.firestore.GeoPoint

data class Point(
    val id: String,
    val address: String,
    val corpus: Long,
    val geoHash: String,
    val coordinates: GeoPoint,
    val properties: Map<String, Int>
)