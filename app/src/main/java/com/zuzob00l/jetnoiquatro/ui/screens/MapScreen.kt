package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.data.samples.sampleMapData
import com.zuzob00l.jetnoiquatro.ui.theme.Default50
import com.zuzob00l.jetnoiquatro.ui.theme.Green700
import com.zuzob00l.jetnoiquatro.ui.theme.Green800
import com.zuzob00l.jetnoiquatro.ui.theme.Neutral900

@Composable
fun MapScreen(
    data: UIState.Map
)
{
   var isDetailVisible by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier
            .fillMaxSize())
    {
      Column()
      {
          Text(
              modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
              color = Color.Black,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              textAlign = TextAlign.Start,
              text = "Twoje zamówienie")
          OrderMap()
      }
        AnimatedVisibility(
            visible = isDetailVisible,
            enter = slideIn { IntOffset(0, 100) } + fadeIn(),
            exit = slideOut { IntOffset(0, 100) } + fadeOut())
        {

            Box(contentAlignment = Alignment.BottomCenter)
            {
                InfoCard(
                    name = data.name,
                    surname = data.surname,
                    sourceAddress = data.sourceAddress,
                    targetAddress = data.targetAddress)
            }
        }
        Box(
            modifier = Modifier.padding(top = 5.dp, bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter)
        {
            OutlinedButton(
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(Green800),
                onClick = { isDetailVisible = !isDetailVisible })
            {
                  if(isDetailVisible) Text(text = "Ukryj szczegóły", color = Color.White)
                  else Text(text = "Zobacz szczegóły", color = Color.White)
            }
        }
    }
}

@Composable
fun InfoCard(
    name: String = "",
    surname: String = "",
    sourceAddress: String = "",
    targetAddress: String = ""
    )
{
    val profileImage = painterResource(id = R.drawable.avatar_0)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = Neutral900)
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.Top)
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp)
                        .clip(CircleShape),
                    painter = profileImage,
                    contentDescription = null)

                Column(
                    modifier = Modifier.padding(end = 5.dp ),
                    verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "$name $surname",
                        fontWeight = FontWeight.Bold,
                        color = Color.White)

                    Text(
                        textAlign = TextAlign.Center,
                        text = "123-345-789",
                        fontWeight = FontWeight.Light,
                        color = Color.White)
                }

                Spacer(modifier = Modifier.weight(1f))

                Surface(
                    shape = CircleShape,
                    color = Default50)
                {
                 IconButton(
                     modifier = Modifier.border(1.dp, color = Color.LightGray, shape = CircleShape),
                     onClick = {})
                 {
                   Icon(
                       modifier = Modifier.size(25.dp, 25.dp),
                       bitmap = ImageBitmap.imageResource(R.drawable.ic_phone),
                       tint = Color.Unspecified,
                       contentDescription = null)
                 }
                }
            }
            //InfoCard:
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .wrapContentHeight()
                    .offset(y = -20.dp),
                shape = RoundedCornerShape(12),
                color = Color.White,
                shadowElevation = 1.dp)
            {
                // val placeImage = ImageBitmap.imageResource(R.drawable.ic_address)
                // val clockImage = ImageBitmap.imageResource(R.drawable.ic_clock)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(26.dp)
                )
                {
                    InfoCardRow(
                        modifier = Modifier.padding(start = 16.dp, top = 10.dp),
                        image = R.drawable.ic_address,
                        address = sourceAddress
                    )

                     Image(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .size(30.dp, 70.dp),
                        colorFilter = ColorFilter.tint(Color.LightGray),
                        painter = painterResource(R.drawable.ic_line),
                        contentDescription = null)

                      InfoCardRow(
                        modifier = Modifier.padding(start = 16.dp, bottom = 10.dp),
                        image = R.drawable.ic_clock,
                        address = targetAddress)
                }
            }
            }
        }
    }


@Composable
fun InfoCardRow(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    address: String = "")
{
    var image = painterResource(id = image)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically)
    {
           IconButton(
               modifier = Modifier
                   .border(1.dp, color = Color.LightGray, shape = CircleShape),
               onClick = { /*TODO*/ })
           {
              Icon(
                  modifier = Modifier.size(25.dp, 25.dp),
                  tint = Green800,
                  painter = image,
                  contentDescription = null)
           }

        Text(
            modifier = Modifier.padding(start = 10.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = address)
    }
}

@Composable
fun OrderMap(modifier: Modifier = Modifier)
{
    //dlugosc i szerokosc geog:
    val startPlace = LatLng(1.35, 103.87)

    //camera implementation:
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startPlace, 10f)
    }

    //map will be placed in Surface:
    Surface(
        modifier = modifier
            .fillMaxSize(),
        shadowElevation = 1.dp)
    {
        //create a Google map:
        GoogleMap(
            modifier = modifier
                .fillMaxSize(),
            cameraPositionState)
        { 
            Marker(
                state = MarkerState(startPlace),
                title = "Singapore",
                snippet = "Marker in singapore")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MapScreenPreview()
{
    MapScreen(data = sampleMapData)
}
//@Preview()
@Composable
fun InfoCardRowPreview() {

    InfoCardRow(image = R.drawable.ic_address)
}