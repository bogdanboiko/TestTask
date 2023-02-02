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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.testtaskintegrio.R
import com.example.testtaskintegrio.presenter.model.Point
import kotlin.math.roundToLong

@Composable
fun PointCard(point: Point, distance: Double) {
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

            val corpus = if (point.corpus == 0L) {
                ""
            } else {
                point.corpus.toString()
            }

            Text(
                text = "$corpus ${point.address}",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${distance.roundToLong()} m",
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )

            ColorBox(point.properties["green"], Color(50, 168, 102))

            ColorBox(point.properties["orange"], Color(224, 200, 76))

            ColorBox(point.properties["blue"], Color(76, 182, 224))

        }
    }
}

@Composable
fun ColorBox(quantity: Long?, color: Color) {
    Row(
        modifier = Modifier.width(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (quantity != null && quantity != 0L) {
            Text(text = "$quantity", color = Color.Black, maxLines = 1)

            Canvas(
                modifier = Modifier
                    .size(size = 30.dp)
            ) {
                drawCircle(color = color)
            }
        }
    }
}