package com.csci448.nestoll.ez_transfer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.nestoll.ez_transfer.R

@Composable
fun Permissions() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.permission_request))
    }
}

@Preview
@Composable
fun PreviewPermissions() {
    Permissions()
}