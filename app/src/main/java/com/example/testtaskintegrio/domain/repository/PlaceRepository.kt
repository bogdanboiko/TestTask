package com.example.testtaskintegrio.domain.repository

import androidx.paging.PagingData
import com.example.testtaskintegrio.domain.model.PointDomain
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    fun getPoints(): Flow<PagingData<PointDomain>>

    suspend fun putPoint(point: PointDomain)
}