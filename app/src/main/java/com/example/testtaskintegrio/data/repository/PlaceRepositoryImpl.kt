package com.example.testtaskintegrio.data.repository

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.mapper.mapToPointEntity
import com.example.testtaskintegrio.domain.model.PointDomain
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import com.firebase.geofire.GeoLocation

class PlaceRepositoryImpl(private val remoteDatasource: PlaceDatasource.Remote) : PlaceRepository {
    override fun getPoints(): PointDomain {
        remoteDatasource.fetchPoints()
        return PointDomain("", "", 4, "", GeoLocation(4.0, 5.0), emptyMap())
    }

    override suspend fun putPoint(point: PointDomain) {
        remoteDatasource.putPoint(point.mapToPointEntity())
    }
}