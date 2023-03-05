package com.csci448.nestoll.ez_transfer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TransferScreen() {
    Column() {
        Text(text = "Transfering files...")
        Column(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp),
                progress = .7f,
                trackColor = Color.LightGray,
                color = Color.Red //progress color
            )
        }
    }
}

@Preview
@Composable
fun PreviewTransfer() {
    TransferScreen()
}