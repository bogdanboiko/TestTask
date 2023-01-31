package com.example.testtaskintegrio.data.remote.mapper

import com.example.testtaskintegrio.data.remote.entity.PointEntity
import com.example.testtaskintegrio.domain.model.PointDomain

fun PointEntity.mapToPointDomain() =
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

fun PointDomain.mapToPointEntity() =
    with(this) {
        PointEntity(
            id = id,
            address = address,
            corpus = corpus,
            geoHash = geoHash,
            coordinates = coordinates,
            properties = properties
        )
    }