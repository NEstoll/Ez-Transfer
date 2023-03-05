package com.csci448.nestoll.ez_transfer.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.csci448.nestoll.ez_transfer.R
import com.csci448.nestoll.ez_transfer.data.Device

@Composable
fun DeviceScreen(deviceList: List<Device>, deviceClicked: (Device) -> () -> Unit) {
    Column() {
        Text(text = stringResource(id = R.string.nearby_devices))
        LazyColumn() {
            items(deviceList) {device -> DeviceCard(device, deviceClicked(device))}
        }
    }
}