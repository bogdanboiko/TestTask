package com.example.testtaskintegrio.presenter.event

import android.location.Location
import com.example.testtaskintegrio.presenter.model.Point

sealed class PointEvent {
    data class UpdateLocationEvent(val location: Location) : PointEvent()
    data class AddPointEvent(val point: Point) : PointEvent()
}
