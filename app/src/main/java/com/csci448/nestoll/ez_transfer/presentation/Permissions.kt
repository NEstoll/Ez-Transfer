package com.csci448.nestoll.ez_transfer.presentation



import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.nestoll.ez_transfer.R



@Composable
fun Permissions() {
    Text(text = stringResource(id = R.string.permission_request))
}

@Preview
@Composable
fun PreviewPermissions() {
    Permissions()
}