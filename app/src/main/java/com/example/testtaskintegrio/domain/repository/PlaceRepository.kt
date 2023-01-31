package com.example.testtaskintegrio.domain.repository

import com.example.testtaskintegrio.domain.model.PointDomain

interface PlaceRepository {
    fun getPoints(): PointDomain

    suspend fun putPoint(point: PointDomain)
}