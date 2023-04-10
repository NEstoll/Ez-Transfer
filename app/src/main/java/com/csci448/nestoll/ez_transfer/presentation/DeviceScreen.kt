package com.csci448.nestoll.ez_transfer.presentation


import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.csci448.nestoll.ez_transfer.R

@Composable
fun DeviceScreen(deviceList: List<BluetoothDevice>, deviceClicked: (BluetoothDevice) -> () -> Unit) {
    Column() {
        Text(text = stringResource(id = R.string.nearby_devices))
        LazyColumn() {
            try {
                items(deviceList) { device -> DeviceCard(device.name, deviceClicked(device)) }
            } catch(e: SecurityException) {
                throw e
            }
        }
    }
}