package com.csci448.nestoll.ez_transfer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.csci448.nestoll.ez_transfer.data.Device
import com.csci448.nestoll.ez_transfer.presentation.DeviceScreen
import com.csci448.nestoll.ez_transfer.presentation.FileSelector
import com.csci448.nestoll.ez_transfer.presentation.TransferScreen
import com.csci448.nestoll.ez_transfer.ui.theme.EzTransferTheme
import java.io.File

class MainActivity : ComponentActivity() {
    val contract = object: ActivityResultContract<String, File?>() {
        override fun createIntent(context: Context, input: String): Intent {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            return intent
        }
        override fun parseResult(resultCode: Int, intent: Intent?): File? {
            if(resultCode != Activity.RESULT_OK) return null
            return intent?.dataString?.let { File(it) }
        }
    }

    val callback = object : ActivityResultCallback<File?> {
        override fun onActivityResult(result: File?) {
            //save files to view model
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val launcher: ActivityResultLauncher<String> = registerForActivityResult(contract, callback)

        setContent {
            val navController = rememberNavController()

            EzTransferTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "myNavGraph") {
                        navigation (route = "myNavGraph", startDestination = "DeviceSelector") {
                            composable(route = "DeviceSelector") {
                                DeviceScreen(deviceList = listOf(Device())) {
                                    device -> {navController.navigate("FileSelector")}
                                }

                            }
                            composable(route = "FileSelector") {
                                launcher.launch("")
//                                FileSelector {navController.navigate("TransferScreen")}
                            }
                            composable(route = "TransferScreen") {
                                TransferScreen()
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
        DeviceScreen(deviceList = listOf(Device(), Device())) { {} }
    }
}

@Preview(showBackground = true)
@Composable
fun FilePreview() {
    EzTransferTheme {
        FileSelector() {}
    }
}

@Preview(showBackground = true)
@Composable
fun TransferPreview() {
    EzTransferTheme {
        TransferScreen()
    }
}