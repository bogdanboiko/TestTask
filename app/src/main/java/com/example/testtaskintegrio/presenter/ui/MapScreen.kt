package com.example.testtaskintegrio.presenter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.testtaskintegrio.PlaceViewModel
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
        ) {}

        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
            MapComponent(changeCameraPosition = {
                cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 16f)
            }, putPoint = {
                viewModel.putPoint(it)
            })
        }
    }
}