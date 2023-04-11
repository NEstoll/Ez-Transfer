package com.csci448.nestoll.ez_transfer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.csci448.nestoll.ez_transfer.R

@Composable
fun FileScreen(addFileClicked: () -> Unit, nextButtonClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.files_screen))
        Button(modifier = Modifier.fillMaxWidth(), onClick = addFileClicked) {
            Text(text = stringResource(id = R.string.add_file))
        }
        Row(modifier = Modifier.fillMaxHeight().align(Alignment.CenterHorizontally)) {
            Button(modifier = Modifier.align(Alignment.Bottom), onClick = nextButtonClicked) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}