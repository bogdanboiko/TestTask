package com.example.testtaskintegrio

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.testtaskintegrio.presenter.PlaceViewModel
import com.example.testtaskintegrio.presenter.theme.TestTaskIntegrioTheme
import com.example.testtaskintegrio.presenter.ui.MapScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: PlaceViewModel by viewModel()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val multiplePermissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )

            TestTaskIntegrioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (multiplePermissionState.allPermissionsGranted) {
                        MapScreen(
                            sendEvent = viewModel::onEvent,
                            myLocationState = viewModel.myLocation.collectAsState(),
                            pointList = viewModel.pointsFlow
                        )
                    } else {
                        val scope = rememberCoroutineScope()
                        Button(onClick = {
                            scope.launch {
                                multiplePermissionState.launchMultiplePermissionRequest()
                            }

                        }) {
                            Text(text = "accept permissions")
                        }

                    }
                }
            }
        }
    }
}