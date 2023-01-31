package com.example.testtaskintegrio.domain.model

import com.firebase.geofire.GeoLocation

data class PointDomain(
    val id: String,
    val address: String,
    val corpus: Int,
    val geoHash: String,
    val coordinates: GeoLocation,
    val properties: Map<String, Int>
)