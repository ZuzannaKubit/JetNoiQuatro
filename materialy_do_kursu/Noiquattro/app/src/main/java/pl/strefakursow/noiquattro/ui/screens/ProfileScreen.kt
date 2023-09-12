package pl.strefakursow.noiquattro.ui.screens

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import pl.strefakursow.noiquattro.R
import pl.strefakursow.noiquattro.data.UiState
import pl.strefakursow.noiquattro.data.samples.sampleProfile
import pl.strefakursow.noiquattro.ui.theme.Green800

@Composable
fun ProfileScreen(
    data: UiState.Profile,
    onHistoryClick: () -> Unit = {},
    onProfileDataClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {},
) {

    Column {
        ProfileHeader()
        ProfileInfo(name = data.name, surname = data.surname, email = data.email)
        ProfileMenu(onHistoryClick, onProfileDataClick, onAddressClick, onPaymentClick)
        ProfileHelp()
    }


}

@Composable
fun ProfileInfo(name: String = "", surname: String = "", email: String = "") {

    Column {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "$name $surname",
            fontStyle = FontStyle.Normal,
            fontSize = 40.sp,
            textAlign = TextAlign.Start)
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            text = email,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
        )
    }

}

@Composable
fun ProfileHelp() {
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom) {

        ProfileButton(
            resourceId = R.drawable.ic_help,
            buttonText = "Pomoc",
            onClick = {}
        )
        ProfileButton(
            resourceId = null,
            buttonText = "Wyloguj się",
            onClick = {})
    }
}

@Composable
fun ProfileMenu(
    onHistoryClick: () -> Unit = {},
    onProfileDataClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {},
) {
    Column {
        ProfileButton(
            resourceId = R.drawable.ic_history,
            buttonText = "Historia",
            onClick = onHistoryClick
        )
        ProfileButton(resourceId = R.drawable.ic_profile,
            buttonText = "Dane",
            onClick = onProfileDataClick)
        ProfileButton(resourceId = R.drawable.ic_address,
            buttonText = "Adres",
            onClick = onAddressClick)
        ProfileButton(resourceId = R.drawable.ic_payments,
            buttonText = "Płatność",
            onClick = onPaymentClick)
    }

}

@Composable
fun ProfileButton(
    @DrawableRes resourceId: Int?,
    buttonText: String,
    onClick: () -> Unit,
) {

    val arrowRight = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)
    val displayIcon = if (resourceId != null)
        ImageBitmap.imageResource(id = resourceId)
    else null

    OutlinedButton(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        onClick = onClick,
        shape = RoundedCornerShape(10)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (displayIcon != null)
                Image(modifier = Modifier
                    .size(25.dp, 25.dp)
                    .padding(end = 10.dp),
                    bitmap = displayIcon, contentDescription = null)

            Text(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                text = buttonText,
                textAlign = TextAlign.Start)

            Image(imageVector = arrowRight,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Green800))
        }
    }
}

@Composable
fun ProfileHeader() {
    val arrowLeft = ImageVector.vectorResource(id = R.drawable.ic_arrow_left)

    Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Icon(imageVector = arrowLeft, contentDescription = null)
        Text(text = "Profil", fontWeight = FontWeight.Bold, fontSize = 22.sp)

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(data = sampleProfile)
}