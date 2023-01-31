package com.example.testtaskintegrio.presenter.model

import com.firebase.geofire.GeoLocation

data class Point(
    val id: String,
    val address: String,
    val corpus: Int,
    val geoHash: String,
    val coordinates: GeoLocation,
    val properties: Map<String, Int>
)