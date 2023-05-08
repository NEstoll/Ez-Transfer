package com.csci448.nestoll.ez_transfer.data

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.ContentValues
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.csci448.nestoll.ez_transfer.TransferPreview
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.UUID

class TransferViewModel() {
    val file: MutableState<File?> = mutableStateOf(null)
    val availableDevices: MutableState<MutableList<BluetoothDevice>> = mutableStateOf(mutableListOf())
    val connectedDevice: MutableState<BluetoothDevice?> = mutableStateOf(null)
    val currentProgress: MutableState<Int> = mutableStateOf(0)
    companion object {
        val instance = TransferViewModel()
        val UUID = UUID(69420, 42069)
    }

    fun connect(bluetoothAdapter: BluetoothAdapter) {
        val thread = ConnectThread(connectedDevice.value?:return, bluetoothAdapter)
        thread.start()
    }

    fun manageMyConnectedSocket(socket: BluetoothSocket) {
        val file_in = file.value?.inputStream()
//        val file_in = FileInputStream(file.value?: return)
//        socket.outputStream.write(file.value?.name?.encodeToByteArray() ?: return)
//        socket.outputStream.write(0)
        file_in?.transferTo(socket.outputStream)
    }

    private inner class ConnectThread(device: BluetoothDevice, val bluetoothAdapter: BluetoothAdapter) : Thread() {

        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            try {
                device.createRfcommSocketToServiceRecord(UUID)
            } catch (e: SecurityException){
                throw e
            }
        }

        override fun run() {
            try {
                // Cancel discovery because it otherwise slows down the connection.
                bluetoothAdapter.cancelDiscovery()

                mmSocket?.let { socket ->
                    // Connect to the remote device through the socket. This call blocks
                    // until it succeeds or throws an exception.
                    socket.connect()

                    // The connection attempt succeeded. Perform work associated with
                    // the connection in a separate thread.
                    manageMyConnectedSocket(socket)
                }
            } catch (e: SecurityException) {
                throw e
            }
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "Could not close the client socket", e)
            }
        }
    }
}