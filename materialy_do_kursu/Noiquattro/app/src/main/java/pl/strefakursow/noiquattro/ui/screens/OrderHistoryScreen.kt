package pl.strefakursow.noiquattro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import pl.strefakursow.noiquattro.R
import pl.strefakursow.noiquattro.data.ItemDetail
import pl.strefakursow.noiquattro.data.Order
import pl.strefakursow.noiquattro.data.UiState
import pl.strefakursow.noiquattro.data.samples.sampleEmptyOrderHistoryData
import pl.strefakursow.noiquattro.data.samples.sampleOrderHistoryData
import pl.strefakursow.noiquattro.ui.theme.Green800

@Composable
fun OrderHistoryScreen(
    data: UiState.OrderHistory,
    onEmptyHistoryClick: () -> Unit = {},
) {

    when (data.orderList.isEmpty()) {
        true -> EmptyOrdersHistory(onEmptyHistoryClick)
        false -> OrdersHistory(data.orderList)
    }

}

@Composable
fun OrdersHistory(orderList: List<Order>) {
    Column {
        OrdersHistoryHeader(orderNumber = orderList.size)
        LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
            items(items = orderList,
                key = { order: Order -> order.item.id }) { order ->
                OrdersHistoryItem(order)
            }
        }
    }

}

@Composable
fun OrdersHistoryItem(order: Order) {

    Surface(modifier = Modifier
        .wrapContentSize()
        .padding(10.dp), elevation = 1.dp,
        shape = RoundedCornerShape(10)) {

        Column(modifier = Modifier.padding(10.dp)) {

            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = "${order.item.orderState}, ${order.item.date}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Text(text = "${order.item.name}...",
                fontWeight = FontWeight.Light)

            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = (order.item.price * order.count).toString(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green800)

                    val arrowRight = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)
                    Icon(imageVector = arrowRight,
                        contentDescription = null,
                        tint = Green800)
                }
            }
        }

    }
}

@Composable
fun OrdersHistoryHeader(orderNumber: Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp, horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart) {

        Text(text = "Historia zamówień ($orderNumber)",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp)
    }

}

@Composable
fun EmptyOrdersHistory(onEmptyHistoryClick: () -> Unit) {

    val emptyHistoryImage = ImageBitmap.imageResource(id = R.drawable.empty_order_history)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            modifier = Modifier.size(200.dp, 200.dp),
            bitmap = emptyHistoryImage,
            contentDescription = null)
        Text(text = "Nie masz jeszcze \nżadnych zamówień",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp)

        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "Złóż pierwsze zamówienie, aby sprawdzić\njego szczegóły",
            textAlign = TextAlign.Center,
            color = Color.Gray)

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(Green800),
            onClick = onEmptyHistoryClick,
            shape = RoundedCornerShape(20)) {
            Text(text = "Zacznij zamówienie", color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmptyHistoryScreenPreview() {
    OrderHistoryScreen(data = sampleEmptyOrderHistoryData)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryScreenPreview() {
    OrderHistoryScreen(data = sampleOrderHistoryData)
}
