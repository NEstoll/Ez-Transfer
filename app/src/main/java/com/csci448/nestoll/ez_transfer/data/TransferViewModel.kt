package com.csci448.nestoll.ez_transfer.data

import android.bluetooth.BluetoothDevice
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.io.File

class TransferViewModel() {
    val file: MutableState<File?> = mutableStateOf(null)
    val availableDevices: MutableState<List<BluetoothDevice>> = mutableStateOf(listOf())
    val connectedDevice: MutableState<BluetoothDevice?> = mutableStateOf(null)
    val currentProgress: MutableState<Int> = mutableStateOf(0)
}