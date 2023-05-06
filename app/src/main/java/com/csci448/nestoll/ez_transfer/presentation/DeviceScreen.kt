package com.csci448.nestoll.ez_transfer.presentation


import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.csci448.nestoll.ez_transfer.R

@Composable
fun DeviceScreen(nextButtonClicked: () -> Unit, deviceList: List<BluetoothDevice>, deviceClicked: (BluetoothDevice) -> () -> Unit) {
    if (deviceList.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.nearby_devices)
            )
            if (deviceList.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.no_devices)
                )
            }
            LazyColumn {
                try {
                    items(deviceList) { device -> DeviceCard(device.name, deviceClicked(device)) }
                } catch (e: SecurityException) {
                    throw e
                }

            }
            Row(modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally)) {
                Button(modifier = Modifier.align(Alignment.Bottom), onClick = nextButtonClicked) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "No connected devices")
        }
    }
}