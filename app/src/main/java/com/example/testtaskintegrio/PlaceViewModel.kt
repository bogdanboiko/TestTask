package com.example.testtaskintegrio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import com.example.testtaskintegrio.presenter.mapper.mapPointToDomain
import com.example.testtaskintegrio.presenter.mapper.mapToPoint
import com.example.testtaskintegrio.presenter.model.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {
    private val _state = MutableStateFlow(listOf<Point>())
    val state = _state.asStateFlow()

    init {
        getPoints()
    }

    fun getPoints() = viewModelScope.launch {
        _state.update { repository.getPoints().map { it.mapToPoint() } }
    }

    fun putPoint(point: Point) = viewModelScope.launch {
        repository.putPoint(point.mapPointToDomain())
    }
}