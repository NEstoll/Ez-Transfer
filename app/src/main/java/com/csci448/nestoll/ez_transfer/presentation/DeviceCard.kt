package com.csci448.nestoll.ez_transfer.presentation

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.csci448.nestoll.ez_transfer.MainActivity

@Composable
fun DeviceCard(deviceName: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = deviceName)
    }
}


@Preview
@Composable
fun PreviewCard() {
    DeviceCard("Device 1") {}
}