package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.data.samples.sampleProfile
import com.zuzob00l.jetnoiquatro.ui.theme.Green800

@Composable
fun ProfileScreen(
    data: UIState.Profile,
    onHistoryClick: () -> Unit = {},
    onProfileDataClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {}
) {
    Column(modifier = Modifier
        .padding(
            top = 16.dp,
            bottom = 32.dp,
            start = 16.dp,
            end = 16.dp))
    {
        ProfileHeader()
        ProfileInfo(
            name = data.name,
            surname = data.surname,
            email = data.email)
        ProfileMenu(
            onAddressClick,
            onHistoryClick,
            onProfileDataClick,
            onPaymentClick
        )
        //ProfileHelp()
    }
}

@Composable
fun ProfileInfo(
    name: String = "",
    surname: String = "",
    email: String = "")
{
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            text = "$name $surname")

        Text(
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Gray,
            text = email)
    }
}

@Composable
fun ProfileHelp() {
    TODO("Not yet implemented")
}

@Composable
fun ProfileMenu(
    onHistoryClick: () -> Unit = {},
    onProfileDataClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {}
) {

    Column(modifier = Modifier.fillMaxHeight())
    {
        ProfileButton(
            text = "Historia",
            resourceId = R.drawable.ic_history,
            onClick = { onHistoryClick })

        ProfileButton(
            text = "Dane",
            resourceId = R.drawable.ic_profile,
            onClick = { onProfileDataClick })

        ProfileButton(
            text = "Adres",
            resourceId = R.drawable.ic_address,
            onClick = { onAddressClick })

        ProfileButton(
            text = "Płatność",
            resourceId = R.drawable.ic_payments,
            onClick = { onPaymentClick })

        Spacer(modifier = Modifier.weight(1f))

        ProfileButton(
            resourceId = R.drawable.ic_help,
            text = "Pomoc",
            onClick = {})

        ProfileButton(
            text = "Wyloguj się",
            resourceId = null,
            onClick = {})
    }
}

@Composable
fun ProfileButton(
    text: String = "",
    @DrawableRes resourceId: Int?,
    onClick: () -> Unit = {}
)
{
    val arrowRight = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)
    val displayIcon = if(resourceId != null ) ImageBitmap.imageResource(id = resourceId)
                      else null

    OutlinedButton(
        modifier = Modifier.padding(bottom = 8.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        border = BorderStroke(color = Color.LightGray, width = 1.dp)
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            //horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            if(displayIcon != null)
            Icon(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(25.dp),
                bitmap = displayIcon,
                tint = Color.DarkGray,
                contentDescription = null)

            Text(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                text = text)

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = arrowRight,
                tint = Green800,
                contentDescription = null)
        }
    }

    
}

@Composable
fun ProfileHeader() {

    val arrowLeft = ImageVector.vectorResource(id = R.drawable.ic_arrow_left)

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        Icon(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(20.dp),
            tint = Color.Black,
            imageVector = arrowLeft,
            contentDescription = null)

        Text(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            text = "Profil")
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview()
{
    ProfileScreen(data = sampleProfile)
}
//@Preview(showBackground = true)
@Composable
fun ProfileMenuPreview() {
    ProfileMenu()
}