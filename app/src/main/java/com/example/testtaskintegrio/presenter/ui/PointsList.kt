package com.example.testtaskintegrio.presenter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.testtaskintegrio.presenter.PlaceViewState
import com.example.testtaskintegrio.presenter.model.Point
import com.firebase.geofire.GeoFireUtils
import com.firebase.geofire.GeoLocation

@Composable
fun PointsList(listItems: LazyPagingItems<Point>, myLocationState: State<PlaceViewState>) {
    val listState = rememberLazyListState()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), state = listState) {
        items(listItems) { point ->
            point?.let {
                val myLocation = myLocationState.value.userLocation

                val pointTest = GeoLocation(myLocation.latitude, myLocation.longitude)
                val distance = GeoFireUtils.getDistanceBetween(
                    pointTest,
                    GeoLocation(point.coordinates.latitude, point.coordinates.longitude)
                )

                PointCard(it, distance)
            }
        }
    }
}