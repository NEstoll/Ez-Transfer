package com.csci448.nestoll.ez_transfer.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.csci448.nestoll.ez_transfer.data.Device

@Composable
fun DeviceScreen(deviceList: List<Device>) {
    Column() {
        Text(text = "Nearby Devices")
        LazyColumn() {
            items(deviceList) {device -> DeviceCard(device)}
        }
    }
}