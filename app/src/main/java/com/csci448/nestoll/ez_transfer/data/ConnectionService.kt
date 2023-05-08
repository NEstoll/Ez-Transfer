package com.csci448.nestoll.ez_transfer.data

import android.app.Service
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.IBinder
import androidx.core.content.getSystemService
import java.io.File

class ConnectionService: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val adapter = (this.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager).adapter
        try {
            val serverSocket =adapter.listenUsingRfcommWithServiceRecord("Ez Tansfer", TransferViewModel.UUID)
            val socket = serverSocket.accept()
            serverSocket.close()
            handleSocket(socket)
        } catch (e: SecurityException) {
            throw e
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun handleSocket(socket: BluetoothSocket) {
        val file = File("Transfered_File")
        file.createNewFile()
        socket.inputStream.transferTo(file.outputStream())
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}