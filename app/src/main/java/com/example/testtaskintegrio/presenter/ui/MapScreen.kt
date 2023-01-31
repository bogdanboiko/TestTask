package com.example.testtaskintegrio.presenter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testtaskintegrio.PlaceViewModel
import com.example.testtaskintegrio.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.androidx.compose.getViewModel

@Composable
fun MapScreen(viewModel: PlaceViewModel = getViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(44.810058, 20.4617586), 16f)
        }

        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true),
            uiSettings = MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            )
        ) {

        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(50.dp)
                .background(Color.Blue, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_add_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}