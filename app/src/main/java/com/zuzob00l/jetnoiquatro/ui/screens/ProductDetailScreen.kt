package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.ui.theme.Default50
import com.zuzob00l.jetnoiquatro.ui.theme.Green700
import com.zuzob00l.jetnoiquatro.ui.theme.Green800
import com.zuzob00l.jetnoiquatro.ui.theme.Neutral900
import pl.strefakursow.noiquattro.data.samples.sampleItemDetailOne
import pl.strefakursow.noiquattro.data.samples.sampleItemDetailScreen

@Composable
fun ProductDetailScreen(
    data: UIState.ItemDetailScreen,
    onItemAdd: (ItemDetail) -> Unit = {},
    onGoToShoppingBag: () -> Unit = {}
) {

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState))
    {
        ProductHeader()
        ProductImage(image = data.item.image)
        ProductDetail(
            item = data.item,
            alreadyAdded = data.alreadyAdded)
    }
}

@Composable
fun ProductDetail(
    item: ItemDetail,
    alreadyAdded: Boolean = false,
    onItemAdd: (ItemDetail) -> Unit = {},
    onGoToShoppingBag: () -> Unit = {})
{
    var isIngredientExpanded by rememberSaveable { mutableStateOf(false) }
    var isCaloriesTableExpanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(
                start = 10.dp,
                end = 10.dp),
        shape = RoundedCornerShape(10)
    ) {
        Column() {
            LazyRow()
            {
                items(items = item.hashTags) { tag ->
                    ProductHashTag(name = tag)
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 8.dp, end = 8.dp)
            )
            {
                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    text = item.name
                )

                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    text = item.price.toString()
                )
            }
            Column() {

                val ingredients =
                    if (isIngredientExpanded) ImageVector.vectorResource(R.drawable.ic_arrow_up)
                    else ImageVector.vectorResource(R.drawable.ic_arrow_down)

                Row(
                    modifier = Modifier
                        .clickable { isIngredientExpanded = !isIngredientExpanded }
                        .padding(top = 45.dp))
                {
                    Text(
                        color = Color.Gray,
                        text = "Składniki"
                    )
                    Icon(
                        imageVector = ingredients,
                        contentDescription = null
                    )
                    if (isIngredientExpanded) {
                        Text(
                            modifier = Modifier.padding(top = 15.dp),
                            fontWeight = FontWeight.Bold,
                            text = item.ingredients
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
////////nutritional values//////////////////////////////////////////

            Column() {

                val calories =
                    if (isCaloriesTableExpanded) ImageVector.vectorResource(R.drawable.ic_arrow_up)
                    else ImageVector.vectorResource(R.drawable.ic_arrow_down)

                Row() {

                    Text(
                        color = Color.Gray,
                        text = "Wartości odżywcze")
                    Icon(
                        imageVector = calories,
                        contentDescription = null
                    )
                }
                if (isCaloriesTableExpanded) {
                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        fontWeight = FontWeight.Bold,
                        text = item.calories
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            ShoppingBagButton(
                alreadyAdded = alreadyAdded,
                onClick = { onItemAdd(item) },
                onGoToShoppingBag = onGoToShoppingBag
            )
        }
    }
}

@Composable
fun ShoppingBagButton(
    alreadyAdded: Boolean,
    onClick: () -> Unit = {},
    onGoToShoppingBag: () -> Unit = {})
{
    val defaultModifier = Modifier
        .padding(vertical = 16.dp)
        .height(48.dp)
        .fillMaxWidth()

    when(alreadyAdded) {
        true -> { OutlinedButton(
            modifier = defaultModifier,
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Neutral900),
            onClick = onGoToShoppingBag)
        {
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart)
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start)
                {
                    Image(
                        modifier = Modifier.size(24.dp, 24.dp),
                        bitmap = ImageBitmap.imageResource(R.drawable.ic_already_added),
                        contentDescription = null)
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        text = "Dodano")
                }
            }
        } }
 //////if product is already added to the shopping bag:

        false -> { OutlinedButton(
            modifier = defaultModifier,
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(Green800),
            onClick = onClick)
        {
            Text(
                color = Color.White,
                fontSize = 20.sp,
                text = "Dodaj do koszyka")
        } }
    }

}

@Composable
fun ProductHashTag(name: String) {

    Surface(
        modifier = Modifier.padding(5.dp),
        shadowElevation = 1.dp,
        color = Default50,
        shape = RoundedCornerShape(10))
    {
        Text(
            modifier = Modifier.padding(7.dp),
            color = Green700,
            text = name)
    }
}
@Composable
fun ProductImage(image: Int) {

    var image = ImageBitmap.imageResource(id = image)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .size(350.dp, 350.dp))
    {
        Image(
            bitmap = image,
            alignment = Alignment.Center,
            contentDescription = null)
    }
}

@Composable
fun ProductHeader() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            tint = Color.Black,
            contentDescription = null)

        Icon(
            modifier = Modifier
                .size(35.dp, 35.dp)
                .clip(shape = CircleShape)
                .background(Default50),
            tint = Color.Black,
            imageVector = ImageVector.vectorResource(R.drawable.ic_favourite_border),
            contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {

    ProductDetailScreen(sampleItemDetailScreen)
}