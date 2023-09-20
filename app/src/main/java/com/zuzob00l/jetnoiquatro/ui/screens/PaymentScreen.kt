package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.data.samples.samplePayment
import com.zuzob00l.jetnoiquatro.ui.theme.Green800


@Composable
fun PaymentScreen(
    data: UIState.Payment,
    onCloseClick: () -> Unit = {},
    onPayClick: () -> Unit = {}
) {

    val roundedDouble by remember {

        val totalAmount = data.orderList.map { it.item.price * it.count }
            .sum()
        val rounded = String.format("%.2f", totalAmount)
        mutableStateOf(rounded)
    }

    Column(modifier = Modifier
        .padding(top = 25.dp, start = 16.dp, end = 16.dp, bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Header(onCloseClick)
        PaymentMethod()
        AddressAndPayment(address = data.userData.address)
        Spacer(modifier = Modifier.weight(1f))
        Cost(onPayClick, roundedDouble)
    }
}

@Composable
fun Cost(
    onPayClick: () -> Unit = {},
    totalAmount: String = "0.00")
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp))
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                text = "Koszt")

            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                text = totalAmount)
        }

        OutlinedButton(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Green800),
            onClick = { onPayClick })
        {
            Text(
                fontSize = 18.sp,
                color = Color.White,
                text = "Zapłać")
        }
    }

}

@Composable
fun AddressAndPayment(
    address: String = "",
    onEditAddressClick: () -> Unit = {}) {

    Row(
        modifier = Modifier.padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(20.dp),
            bitmap = ImageBitmap.imageResource(R.drawable.ic_address),
            tint = Color.Black,
            contentDescription = null
        )

        Column()
        {
            OrderAddress(
                text = "Adres",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            OrderAddress(
                text = address,
                color = Color.Gray
            )

            OrderAddress(
                text = "Warszawa 00-000",
                color = Color.Gray
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))

        Box(contentAlignment = Alignment.CenterEnd) {
            OutlinedIconButton(
                modifier = Modifier.clickable { onEditAddressClick },
                shape = RoundedCornerShape(20),
                border = BorderStroke(color = Color.LightGray, width = 1.dp),
                onClick = { onEditAddressClick })
            {
                Icon(
                    modifier = Modifier.size(20.dp),
                    bitmap = ImageBitmap.imageResource(R.drawable.ic_edit),
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        }
    }
}
@Composable
fun OrderAddress(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 18.sp,
    color: Color
) {
    Text(
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        text = text)
}

@Composable
fun PaymentMethod() {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(10))
    {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {

            val paymentIcon = ImageBitmap.imageResource(R.drawable.ic_visa_logo)

            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 20.dp),
                bitmap = paymentIcon,
                tint = Color.Unspecified,
                contentDescription = null)

            Column {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "***1234")

                Text(
                    color = Color.Gray,
                    text = "Metoda płatności")
            }
        }
    }
}

@Composable
fun Header(
    onCloseClick: () -> Unit = {}
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 30.dp),
    horizontalArrangement = Arrangement.Center)
    {
        Text(
           modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = "Płatnośc")

        IconButton(
            modifier = Modifier,
           // border = BorderStroke(color = Color.LightGray, width = 1.dp),
           // shape = RoundedCornerShape(20),
            onClick = { onCloseClick })
        {

                Icon(
                    modifier = Modifier.size(15.dp),
                    bitmap = ImageBitmap.imageResource(R.drawable.ic_close),
                    tint = Color.Gray,
                    contentDescription = null
                )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
   PaymentScreen(data = samplePayment)
}
