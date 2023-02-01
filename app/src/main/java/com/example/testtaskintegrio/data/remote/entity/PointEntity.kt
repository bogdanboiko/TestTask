package com.example.testtaskintegrio.data.remote.entity

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.GeoPoint

data class PointEntity(
    @get:Exclude val id: String,
    val address: String,
    val corpus: Long,
    val geoHash: String,
    val coordinates: GeoPoint,
    val properties: Map<String, Long>
)