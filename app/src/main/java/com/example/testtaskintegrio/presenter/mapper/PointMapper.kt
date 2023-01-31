package com.example.testtaskintegrio.presenter.mapper

import com.example.testtaskintegrio.domain.model.PointDomain
import com.example.testtaskintegrio.presenter.model.Point

fun Point.mapPointToDomain() =
    with(this) {
        PointDomain(
            id = id,
            address = address,
            corpus = corpus,
            geoHash = geoHash,
            coordinates = coordinates,
            properties = properties
        )
    }

fun PointDomain.mapToPoint() =
    with(this) {
        Point(
            id = id,
            address = address,
            corpus = corpus,
            geoHash = geoHash,
            coordinates = coordinates,
            properties = properties
        )
    }