package com.example.testtaskintegrio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import com.example.testtaskintegrio.presenter.mapper.mapPointToDomain
import com.example.testtaskintegrio.presenter.model.Point
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {
    fun getPoints() {
        repository.getPoints()
    }

    fun putPoint(point: Point) = viewModelScope.launch {
        repository.putPoint(point.mapPointToDomain())
    }
}