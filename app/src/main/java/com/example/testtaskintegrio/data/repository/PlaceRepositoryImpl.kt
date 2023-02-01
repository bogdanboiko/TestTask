package com.example.testtaskintegrio.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.PointPagingSource
import com.example.testtaskintegrio.data.remote.mapper.mapToPointEntity
import com.example.testtaskintegrio.domain.model.PointDomain
import com.example.testtaskintegrio.domain.repository.PlaceRepository

class PlaceRepositoryImpl(
    private val remoteDatasource: PlaceDatasource.Remote,
    private val pagingSource: PointPagingSource
) : PlaceRepository {
    override fun getPoints() = Pager(PagingConfig(pageSize = 5)) {
        pagingSource
    }.flow

    override suspend fun putPoint(point: PointDomain) {
        remoteDatasource.putPoint(point.mapToPointEntity())
    }
}