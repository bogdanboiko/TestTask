package com.example.testtaskintegrio.presenter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.testtaskintegrio.presenter.model.Point
import com.google.android.gms.maps.model.LatLng

@Composable
fun PointsList(listItems: LazyPagingItems<Point>, myLocationState: State<LatLng>) {
    val listState = rememberLazyListState()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), state = listState) {
        items(listItems) { point ->
            point?.let { PointCard(it, myLocationState) }
        }
    }
}