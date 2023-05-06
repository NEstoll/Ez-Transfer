package com.csci448.nestoll.ez_transfer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.nestoll.ez_transfer.data.TransferViewModel

@Composable
fun TransferScreen(viewModel: TransferViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Transferring file: " + viewModel.file.value?.name)
        Column(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp),
                progress = viewModel.currentProgress.value.toFloat() / 100.0f,
                trackColor = Color.LightGray,
                color = Color.Red //progress color
            )
        }
    }
}

@Preview
@Composable
fun PreviewTransfer() {
    TransferScreen(TransferViewModel())
}