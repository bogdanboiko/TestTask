package com.example.testtaskintegrio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import com.example.testtaskintegrio.presenter.mapper.mapPointToDomain
import com.example.testtaskintegrio.presenter.mapper.mapToPoint
import com.example.testtaskintegrio.presenter.model.Point
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {
    var pointsFlow = updateFlow()


    private fun updateFlow() = repository.getPoints()
        .map { pagingData -> pagingData.map { point -> point.mapToPoint() } }
        .cachedIn(viewModelScope)

    fun putPoint(point: Point) = viewModelScope.launch {
        repository.putPoint(point.mapPointToDomain())
    }
}