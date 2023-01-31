package com.example.testtaskintegrio.presenter.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testtaskintegrio.R
import com.example.testtaskintegrio.presenter.model.Point
import com.firebase.geofire.GeoFireUtils
import com.firebase.geofire.GeoLocation

@Composable
fun PointCard(point: Point) {
    Card(modifier = Modifier.fillMaxWidth(), backgroundColor = Color.White) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_location_city_24),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp),
                tint = Color.Black
            )

            Text(
                text = point.address,
                color = Color.Black,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )

            val pointTest = GeoLocation(49.4360412, 32.0672931)
            val geoHashTest = GeoFireUtils.getGeoHashForLocation(pointTest)
            val distance = GeoFireUtils.getDistanceBetween(
                pointTest,
                GeoLocation(point.coordinates.latitude, point.coordinates.longitude)
            )

            Text(text = distance.toString(), color = Color.Black, maxLines = 1, modifier = Modifier.weight(1f))

            ColorBox(5, Color.Green)

            ColorBox(5, Color.Red)

            ColorBox(5, Color.Blue)

        }
    }
}

@Composable
fun ColorBox(quantity: Int, color: Color) {
    Row(
        modifier = Modifier.width(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (quantity != 0) {
            Text(text = quantity.toString(), color = Color.Black, maxLines = 1)

            Canvas(
                modifier = Modifier
                    .size(size = 30.dp)
            ) {
                drawCircle(color = color)
            }
        }
    }
}