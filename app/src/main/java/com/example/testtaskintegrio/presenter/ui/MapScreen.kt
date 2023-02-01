package com.example.testtaskintegrio.presenter.ui

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.testtaskintegrio.presenter.event.PointEvent
import com.example.testtaskintegrio.presenter.model.Point
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.Flow

@SuppressLint("MissingPermission")
@Composable
fun MapScreen(
    sendEvent: (PointEvent) -> Unit,
    myLocationState: State<LatLng>,
    pointList: Flow<PagingData<Point>>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(44.810058, 20.4617586), 16f)
        }

        cameraPositionState.position = CameraPosition.fromLatLngZoom(myLocationState.value, 16f)

        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true),
            uiSettings = MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            )
        ) {}

        val updateMyCurrentLocation: (Location) -> Unit = {
            sendEvent(PointEvent.UpdateLocationEvent(it))
        }

        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(LocalContext.current)

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            updateMyCurrentLocation(it)
        }

        Column(
            modifier = Modifier
                .heightIn(50.dp, 330.dp)
                .align(Alignment.BottomEnd)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MapComponent(changeCameraPosition = {
                cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 16f)
            }, putPoint = {
                sendEvent(PointEvent.AddPointEvent(it))
            },
                listState = pointList.collectAsLazyPagingItems(),
                updateMyCurrentLocation = updateMyCurrentLocation,
                myLocationState = myLocationState
            )
        }
    }
}