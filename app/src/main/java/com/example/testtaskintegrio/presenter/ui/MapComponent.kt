package com.example.testtaskintegrio.presenter.ui

import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.testtaskintegrio.R
import com.example.testtaskintegrio.presenter.model.Point
import com.example.testtaskintegrio.presenter.ui.dialog.AddressDialog
import com.firebase.geofire.GeoFireUtils
import com.firebase.geofire.GeoLocation
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import java.util.*

@Composable
fun MapComponent(
    changeCameraPosition: (LatLng) -> Unit,
    putPoint: (Point) -> Unit,
    listState: LazyPagingItems<Point>,
    updateMyCurrentLocation: (Location) -> Unit,
    myLocationState: State<LatLng>
) {
    val geocoder = Geocoder(LocalContext.current, Locale.getDefault())
    val context = LocalContext.current

    val showCustomDialog = remember {
        mutableStateOf(false)
    }

    if (showCustomDialog.value) {
        AddressDialog(onDismissRequest = {
            showCustomDialog.value = false
        },
            onSendPrintedLocation = { location, corpus ->
                val addressList = geocoder.getFromLocationName(location, 1)
                if (addressList != null && addressList.isNotEmpty()) {
                    changeCameraPosition(
                        LatLng(
                            addressList[0].latitude,
                            addressList[0].longitude
                        )
                    )

                    val coordinates =
                        GeoPoint(addressList[0].latitude, addressList[0].longitude)
                    val geoHash = GeoFireUtils.getGeoHashForLocation(
                        GeoLocation(
                            coordinates.latitude,
                            coordinates.longitude
                        )
                    )

                    val greenProperty = (0..10).random().toLong()

                    val blueProperty = (0..10).random().toLong()

                    val orangeProperty = (0..10).random().toLong()

                    putPoint(
                        Point(
                            id = "",
                            address = location,
                            corpus = corpus,
                            geoHash = geoHash,
                            coordinates = coordinates,
                            properties = mapOf(
                                "green" to greenProperty,
                                "orange" to orangeProperty,
                                "blue" to blueProperty
                            )
                        )
                    )
                } else {
                    Toast.makeText(context, "No such location", Toast.LENGTH_LONG).show()
                }
            },
            findAndUpdateMyLocation = {
                updateMyCurrentLocation(it)
                val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                if (addresses != null && addresses.isNotEmpty()) {
                    addresses[0].getAddressLine(0)
                } else {
                    ""
                }
            })
    }

    IconButton(
        onClick = { showCustomDialog.value = true },
        modifier = Modifier
            .size(50.dp)
            .background(Color.Blue, shape = CircleShape)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_baseline_add_24),
            contentDescription = null,
            tint = Color.White
        )
    }

    PointsList(listState, myLocationState)
}