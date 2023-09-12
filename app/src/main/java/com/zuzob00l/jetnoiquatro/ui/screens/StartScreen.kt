package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.ui.theme.Green800

//@Preview(showBackground = true)
@Composable
fun StartScreen(
    onStartClick: () -> Unit = { })  {

    val image = ImageBitmap.imageResource(id = R.drawable.start_screen_background)

    Image(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        bitmap = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = 0.5f)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom) {

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            text = "Noi Quattro")

        Text(
            modifier = Modifier
                .padding(bottom = 100.dp),
            fontSize = 25.sp,
            color = Color.White,
            text = "Włoska Pizzeria")

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp)
                .height(48.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Green800),
            onClick = { /*TODO*/ }) {

            val iconArrow = ImageVector.vectorResource(R.drawable.ic_arrow_right)

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(
                    fontSize = 15.sp,
                    text = "Zaloguj się")
                Icon(
                    modifier = Modifier
                        .padding(10.dp),
                    imageVector = iconArrow,
                    contentDescription = null)

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    StartScreen()
}