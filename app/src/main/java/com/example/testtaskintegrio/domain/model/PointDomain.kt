package com.example.testtaskintegrio.domain.model

import com.google.firebase.firestore.GeoPoint

data class PointDomain(
    val id: String,
    val address: String,
    val corpus: Long,
    val geoHash: String,
    val coordinates: GeoPoint,
    val properties: Map<String, Long>
)