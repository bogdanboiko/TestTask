package com.example.testtaskintegrio.presenter

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.testtaskintegrio.domain.repository.PlaceRepository
import com.example.testtaskintegrio.presenter.event.PointEvent
import com.example.testtaskintegrio.presenter.mapper.mapPointToDomain
import com.example.testtaskintegrio.presenter.mapper.mapToPoint
import com.example.testtaskintegrio.presenter.model.Point
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {
    val pointsFlow = updateFlow()

    private val _myLocation = MutableStateFlow(LatLng(0.0, 0.0))
    val myLocation = _myLocation.asStateFlow()


    fun onEvent(event: PointEvent) {
        when (event) {
            is PointEvent.UpdateLocationEvent -> updateMyLocation(event.location)
            is PointEvent.AddPointEvent -> putPoint(event.point)
        }
    }

    private fun updateFlow() = repository.getPoints()
        .map { pagingData -> pagingData.map { point -> point.mapToPoint() } }
        .cachedIn(viewModelScope)

    private fun putPoint(point: Point) = viewModelScope.launch {
        repository.putPoint(point.mapPointToDomain())
    }

    private fun updateMyLocation(location: Location) {
        _myLocation.tryEmit(LatLng(location.latitude, location.longitude))
    }
}