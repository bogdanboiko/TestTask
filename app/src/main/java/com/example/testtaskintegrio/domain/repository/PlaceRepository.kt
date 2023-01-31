package com.example.testtaskintegrio.domain.repository

import com.example.testtaskintegrio.domain.model.PointDomain

interface PlaceRepository {
    suspend fun getPoints(): List<PointDomain>

    suspend fun putPoint(point: PointDomain)
}