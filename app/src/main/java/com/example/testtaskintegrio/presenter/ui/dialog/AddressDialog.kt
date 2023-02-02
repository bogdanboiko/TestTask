package com.example.testtaskintegrio.presenter.ui.dialog

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.testtaskintegrio.R
import com.google.android.gms.location.LocationServices


@SuppressLint("MissingPermission")
@Composable
fun AddressDialog(
    onDismissRequest: () -> Unit,
    onSendPrintedLocation: (String, Long) -> Unit,
    findAndUpdateMyLocation: (Location) -> String
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            elevation = 10.dp,
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val addressInput = remember { mutableStateOf("") }
                val context = LocalContext.current
                val fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(context)

                IconButton(
                    onClick = {
                        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                            addressInput.value = (findAndUpdateMyLocation(it))
                        }
                    },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_my_location_24),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Blue
                    )
                }

                Column {
                    TextField(
                        value = addressInput.value,
                        onValueChange = { addressInput.value = it },
                        placeholder = { Text(text = "Enter address") },
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        singleLine = true
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        val corpusInput = remember { mutableStateOf(TextFieldValue()) }

                        TextField(
                            value = corpusInput.value,
                            onValueChange = { corpusInput.value = it },
                            placeholder = { Text(text = "0") },
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .width(60.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Text(
                            text = "BAL", modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )

                        Button(
                            onClick = {
                                val corpus = if (corpusInput.value.text.isEmpty()) {
                                    0L
                                } else {
                                    corpusInput.value.text.toLong()
                                }

                                if (addressInput.value.isNotEmpty()) {
                                    onSendPrintedLocation(addressInput.value, corpus)
                                    onDismissRequest()
                                }

                            },
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 10.dp, bottom = 10.dp)
                        ) {
                            Text(text = "Submit", modifier = Modifier)
                        }
                    }
                }
            }
        }
    }
}