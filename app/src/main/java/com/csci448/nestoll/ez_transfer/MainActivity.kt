package com.csci448.nestoll.ez_transfer

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.*
import com.csci448.nestoll.ez_transfer.data.ConnectionService
import com.csci448.nestoll.ez_transfer.data.TransferViewModel
import com.csci448.nestoll.ez_transfer.presentation.DeviceScreen
import com.csci448.nestoll.ez_transfer.presentation.FileScreen
import com.csci448.nestoll.ez_transfer.presentation.Permissions
import com.csci448.nestoll.ez_transfer.presentation.TransferScreen
import com.csci448.nestoll.ez_transfer.ui.theme.EzTransferTheme
import java.io.File

class MainActivity : ComponentActivity() {
    //create bluetooth handler
//    private val bluetoothManager: BluetoothManager? = getSystemService(BluetoothManager::class.java)
//    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager?.adapter
    //create view model
    private val viewModel = TransferViewModel.instance

    //create contract/callback for file selection
    private val fileContract = object: ActivityResultContract<String, File?>() {
        override fun createIntent(context: Context, input: String): Intent {
            return Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "*/*" //get all files and file types
//                putExtra(Intent.EXTRA_TEXT, input)
            }
        }
        override fun parseResult(resultCode: Int, intent: Intent?): File? {
            if(resultCode != Activity.RESULT_OK) return null
            return intent?.dataString?.let { File(it) } //create file from path
        }
    }
    private val fileCallback = object : ActivityResultCallback<File?> {
        override fun onActivityResult(result: File?) {
            //save files
            viewModel.file.value = result
            //TODO display somewhere?
        }
    }



    @SuppressLint("RestrictedApi")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create launcher so we can start file selector
        val fileLauncher: ActivityResultLauncher<String> = registerForActivityResult(fileContract, fileCallback)
        //check permissions (requires higher api?)
        val hasPermission = ContextCompat.checkSelfPermission( this, Manifest.permission.BLUETOOTH_CONNECT ) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            val bluetoothManager: BluetoothManager =
                this@MainActivity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            viewModel.availableDevices.value = bluetoothManager.adapter.bondedDevices.toMutableList()
            bluetoothManager.adapter.cancelDiscovery()
            bluetoothManager.adapter.startDiscovery()
        }
        Intent(this, ConnectionService::class.java).also { intent -> startService(intent) }
        setContent {
            val navController = rememberNavController()
            EzTransferTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopAppBar(
                            navigationIcon = if (navController.previousBackStackEntry != null) {
                                {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = stringResource(R.string.menu_back_desc)
                                        )
                                    }
                                }
                            } else {
                                { }
                            },
                            title = { Text(stringResource(R.string.app_name)) }
                        )
                        NavHost(navController = navController, startDestination = "myNavGraph") {
                            navigation(
                                route = "myNavGraph",
                                startDestination = if (!hasPermission) "Permissions" else "FileSelector"
                            ) {
                                composable(route = "Permissions") {
                                    //permissions not granted, ask user for them
                                    Permissions()
                                }

                                composable(route = "FileSelector") {
                                    //launch file selector, then go to device screen
                                    FileScreen (
                                        addFileClicked = {
                                            fileLauncher.launch("")
                                        },
                                        nextButtonClicked = {
                                            if (viewModel.file.value != null) navController.navigate("DeviceSelector")
                                            else Toast.makeText(this@MainActivity,  "Please select file", Toast.LENGTH_SHORT).show()
                                        }
                                    )
                                }
                                composable(route = "DeviceSelector") {

//                                    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
//                                    val deviceList: MutableList<BluetoothDevice> = mutableListOf()
//                                    pairedDevices?.forEach { device ->
//                                        deviceList.add(device)
//                                    }
                                    DeviceScreen(deviceList = viewModel.availableDevices.value,
                                        nextButtonClicked = {
                                            if (viewModel.connectedDevice.value != null) navController.navigate("TransferScreen")
                                            else Toast.makeText(this@MainActivity, "Please select device", Toast.LENGTH_SHORT).show()
                                        },
                                    ) { device ->
                                        {
                                            viewModel.connectedDevice.value = device
                                            viewModel.connect((this@MainActivity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter)
                                            navController.navigate("TransferScreen")
                                        }
                                    }
                                }
                                composable(route = "TransferScreen") {
                                    TransferScreen(viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EzTransferTheme {
        DeviceScreen(deviceList = TransferViewModel().availableDevices.value, nextButtonClicked = {}) { {} }
    }
}


@Preview(showBackground = true)
@Composable
fun TransferPreview() {
    EzTransferTheme {
        TransferScreen(viewModel = TransferViewModel())
    }
}