package com.csci448.nestoll.ez_transfer.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.nestoll.ez_transfer.data.Device

@Composable
fun DeviceCard(device: Device) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Device 1")
    }
}


@Preview
@Composable
fun PreviewCard() {
    DeviceCard(Device())
}