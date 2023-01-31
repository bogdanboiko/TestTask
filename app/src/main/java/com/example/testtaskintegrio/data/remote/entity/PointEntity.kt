package com.example.testtaskintegrio.data.remote.entity

import com.firebase.geofire.GeoLocation
import com.google.firebase.firestore.Exclude

data class PointEntity(
    @get:Exclude val id: String,
    val address: String,
    val corpus: Int,
    val geoHash: String,
    val coordinates: GeoLocation,
    val properties: Map<String, Int>
)