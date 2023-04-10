package com.csci448.nestoll.ez_transfer.data

import android.bluetooth.BluetoothDevice
import java.io.File

class TransferViewModel() {
    val fileList: List<File> = listOf()
    val deviceList: List<BluetoothDevice> = listOf()
}