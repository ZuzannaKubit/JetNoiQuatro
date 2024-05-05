package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.ItemDetail
import com.zuzob00l.jetnoiquatro.data.Order
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.data.samples.sampleEmptyOrderHistoryData
import com.zuzob00l.jetnoiquatro.data.samples.sampleOrderHistoryData
import com.zuzob00l.jetnoiquatro.ui.theme.Green800

@Composable
fun OrderHistoryScreen(
    data: UIState.OrderHistory,
    onEmptyHistoryClick: () -> Unit = {},
)
{
    when(data.orderList.isEmpty()) {
        true -> EmptyOrderHistory()
        else -> OrderHistory(data.orderList)
    }
}

@Composable
fun OrderHistory(orderList: List<Order>)
{
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp))
    {
        HistoryHrader(orderNumber = orderList.size)
        LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {

            items(
                items = orderList,
                key = { order: Order -> order.item.id }) { order ->

                OrderHistoryItem(order)
            }
        }
    }
}

@Composable
fun OrderHistoryItem(order: Order)
{
   Surface(
       modifier = Modifier
           .fillMaxWidth()
           .padding(bottom = 16.dp)
           .wrapContentHeight(),
       shadowElevation = 1.dp,
       shape = RoundedCornerShape(10)
   )
   {
       Column(modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp))
       {
           Text(
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               text = "${order.item.orderState}, ${order.item.date}")

           Text(
               modifier = Modifier.padding(top = 3.dp),
               color = Color.Gray,
               text = "${order.item.ingredients}")

           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 5.dp),
               contentAlignment = Alignment.CenterEnd)
           {
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.End)
               {
                 Text(
                     modifier = Modifier.padding(end = 15.dp),
                     fontSize = 25.sp,
                     fontWeight = FontWeight.Bold,
                     color = Green800,
                     text = "${order.item.price}")

                   Icon(
                       imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                       tint = Green800,
                       contentDescription = null)
               }
           }
       }
   }
}

@Composable
fun HistoryHrader(orderNumber: Int)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.CenterStart)
    {
       Text(
           fontSize = 25.sp,
           fontWeight = FontWeight.Bold,
           text = "Historia Zamówień($orderNumber)")
    }
}
@Composable
fun EmptyOrderHistory(onEmptyHistoryClick: () -> Unit = {})
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        val emptyHistoryImage = ImageBitmap.imageResource(R.drawable.empty_order_history)

        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 20.dp),
            bitmap = emptyHistoryImage,
            contentDescription = null)

        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = "Nie masz jeszcze \nżadnych zamowień")

        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            fontSize = 18.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            text = "Złóż pierwsze zamówienie, aby sprawdzić\njego szczegóły")

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Green800),
            onClick = { onEmptyHistoryClick })
        {
            Text(
                fontSize = 18.sp,
                color = Color.White,
                text = "Zacznij zamówienie")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OrderHistoryPreview() {

    OrderHistoryScreen(data = sampleOrderHistoryData)

}
@Preview(showBackground = true)
@Composable
fun EmptyOrderHistoryPreview() {

    OrderHistoryScreen(data = sampleEmptyOrderHistoryData)

}