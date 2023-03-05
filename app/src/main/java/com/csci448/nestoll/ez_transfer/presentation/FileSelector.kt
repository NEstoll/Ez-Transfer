package com.csci448.nestoll.ez_transfer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FileSelector() {
    Column() {
        Text(text = "File 1")
        Text(text = "File 2")
        Text(text = "File 3")
        Text(text = "File 4")
        Text(text = "File 5")
        Text(text = "File 7")
    }
}

@Preview
@Composable
fun PreviewFileSelector() {
    FileSelector()
}