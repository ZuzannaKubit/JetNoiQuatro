package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.ItemDetail
import com.zuzob00l.jetnoiquatro.data.Order
import com.zuzob00l.jetnoiquatro.ui.theme.Green800
import com.zuzob00l.jetnoiquatro.data.samples.sampleShoppingBag

@Composable
fun ShoppingBagScreen(
    modifier: Modifier = Modifier,
    shoppingList: List<Order>,
    roundedDouble: Double = 0.0,
    onDecrementOrderNumber: (ItemDetail) -> Unit = {},
    onIncrementClick: (ItemDetail) -> Unit = {},
    onPaymentClick: () -> Unit = {})
{
    Column()
    {
        Box(modifier = Modifier
            .padding(start = 16.dp, top = 25.dp)) {

        Text(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = "Koszyk") }

        //LazyColumn list:
        ShoppingBagList(
            modifier = Modifier.weight(1f),
            shoppingList,
            onDecrementOrderNumber,
            onDecrementOrderNumber)
        //summary:
        SumUp(roundedDouble = roundedDouble)


    }
}

@Composable
fun SumUp(
    roundedDouble: Double,
    onPaymentClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        SumRowText(
            fontWeight = FontWeight.Light,
            textSize = 18.sp,
            leftText = "Razem",
            rightText = roundedDouble.toString())

        SumRowText(
            fontWeight = FontWeight.Light,
            textSize = 18.sp,
            leftText = "Koszt Dostawy",
            rightText = "10")

        SumRowText(
            fontWeight = FontWeight.Bold,
            textSize = 25.sp,
            leftText = "Koszt całkowity",
            rightText = (roundedDouble + 10).toString())

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(Green800),
            shape = RoundedCornerShape(10),
            onClick = { onPaymentClick })
        {
            Text(
               // modifier = Modifier.padding(10.dp),
                text = "Płatność",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
        }
    }
}

@Composable
fun SumRowText(
    textSize: TextUnit,
    fontWeight: FontWeight,
    leftText: String,
    rightText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = leftText,
            fontWeight = fontWeight,
            fontSize = textSize,
            textAlign = TextAlign.End)
    }

}

@Composable
fun ShoppingBagList(
    modifier: Modifier = Modifier,
    shoppingList: List<Order>,
    onDecrementOrderNumber: (ItemDetail) -> Unit,
    onIncrementOrderNumber: (ItemDetail) -> Unit
    ) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 10.dp) //the last element of our LazyColumn will be visible
    )
    {
        items(items = shoppingList) { order ->

           ShoppingBagItem(
               order = order,
               onDecrementClick = onDecrementOrderNumber,
               onIncrementClick = onIncrementOrderNumber) 
        } 
    } 
}

@Composable
fun ShoppingBagItem(
    order: Order,
    onIncrementClick: (ItemDetail) -> Unit = {},
    onDecrementClick: (ItemDetail) -> Unit = {})
{
    val pizza_image = ImageBitmap.imageResource(order.item.image)
    
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        shape = RoundedCornerShape(20),
        shadowElevation = 1.dp)
    {
        Row() {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    modifier = Modifier.size(120.dp,120.dp),
                    bitmap = pizza_image, 
                    contentDescription = null)

                Spacer(modifier = Modifier.width(10.dp))

                Column() {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = 10.dp),
                        fontWeight = FontWeight.Bold,
                        text = order.item.name
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        val addIcon = ImageVector.vectorResource(R.drawable.ic_add)
                        val subIcon = ImageBitmap.imageResource(R.drawable.ic_minus)

                        OutlinedIconButton(
                            modifier = Modifier,
                            border = BorderStroke(color = Color.LightGray, width = 1.dp),
                            shape = RoundedCornerShape(20),
                            onClick = { onIncrementClick })
                        {
                            Icon(
                                imageVector = addIcon,
                                tint = Green800,
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            text = order.count.toString()
                        )

                        OutlinedIconButton(
                            border = BorderStroke(color = Color.LightGray, width = 1.dp),
                            shape = RoundedCornerShape(20),
                            onClick = { onDecrementClick })
                        {
                            Icon(
                                bitmap = subIcon,
                                tint = Color.Black,
                                contentDescription = null
                            )
                        }
                    }
                }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        text = order.item.price.toString())

            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ShoppingBagScreenPreview() {
    ShoppingBagScreen(shoppingList = sampleShoppingBag.itemList)
}

@Preview
@Composable
fun ShoppingBagItemPreview() {
    
    ShoppingBagItem(order = sampleShoppingBag.itemList[0])
}
