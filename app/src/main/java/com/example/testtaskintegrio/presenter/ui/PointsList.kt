package com.example.testtaskintegrio.presenter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import com.example.testtaskintegrio.presenter.model.Point

@Composable
fun PointsList(listState: State<List<Point>>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(listState.value) { point ->
            PointCard(point)
        }
    }
}