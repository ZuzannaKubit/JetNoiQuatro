package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.UIState

@Composable
fun ProfileScreen(
    data: UIState.Profile,
    onHistoryClick: () -> Unit = {},
    onProfileDataClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {}
) {
    Column(modifier = Modifier.padding(16.dp))
    {
        ProfileHeader()
        ProfileMenu()
        ProfileHelp()
    }
}

@Composable
fun ProfileHelp() {
    TODO("Not yet implemented")
}

@Composable
fun ProfileMenu() {
    TODO("Not yet implemented")
}

@Composable
fun ProfileHeader() {

    val arrowLeft = ImageVector.vectorResource(id = R.drawable.ic_arrow_left)

    Row(modifier = Modifier.fillMaxWidth())
    {
        
    }
}
