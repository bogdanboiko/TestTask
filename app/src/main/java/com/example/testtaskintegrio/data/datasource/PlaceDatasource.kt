package com.example.testtaskintegrio.data.datasource

import com.example.testtaskintegrio.data.remote.entity.PointEntity


interface PlaceDatasource {
    interface Remote {
        suspend fun fetchPoints(): List<PointEntity>
        suspend fun putPoint(point: PointEntity)
    }
}