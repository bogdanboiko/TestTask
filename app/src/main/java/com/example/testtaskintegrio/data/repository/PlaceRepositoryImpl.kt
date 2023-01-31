package com.example.testtaskintegrio.data.repository

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.mapper.mapToPointDomain
import com.example.testtaskintegrio.data.remote.mapper.mapToPointEntity
import com.example.testtaskintegrio.domain.model.PointDomain
import com.example.testtaskintegrio.domain.repository.PlaceRepository

class PlaceRepositoryImpl(private val remoteDatasource: PlaceDatasource.Remote) : PlaceRepository {
    override suspend fun getPoints(): List<PointDomain> {
        return remoteDatasource.fetchPoints().map { it.mapToPointDomain() }
    }

    override suspend fun putPoint(point: PointDomain) {
        remoteDatasource.putPoint(point.mapToPointEntity())
    }
}