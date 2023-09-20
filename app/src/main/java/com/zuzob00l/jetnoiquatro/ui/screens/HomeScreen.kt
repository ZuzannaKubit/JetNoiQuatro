package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.ItemDetail
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.ui.theme.Green800
import com.zuzob00l.jetnoiquatro.ui.theme.Neutral900
import com.zuzob00l.jetnoiquatro.data.samples.sampleHeader
import com.zuzob00l.jetnoiquatro.data.samples.sampleHomeData

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    data: UIState.Home,
    onItemClick: (ItemDetail) -> Unit,
    onProfileClick: () -> Unit,
    onSearch: (String) -> Unit)
{
    val scrollState = rememberScrollState()
    var text by remember { mutableStateOf("") }

    var selectedCategoryTab by rememberSaveable { mutableStateOf("Pizza") }

  Column(
      modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
          .padding(
              start = 2.dp,
              end = 2.dp,
              bottom = 10.dp
          ))
  {
      HomeHeader(
          address = data.userData.address,
          onProfileClick = onProfileClick)
      WelcomeText(
          data.userData.name
      )
      SearchField(
          text = text,
          onSearch = {
          text = it
          onSearch(it)})
      PromotionAdds()

      Spacer(modifier = Modifier.height(15.dp))
      
    OfferList(
        headers = sampleHeader,
        products = data.products,
        //selectedCategoryTab = ,
        onTabClick = { category -> selectedCategoryTab = category },
        onItemClick = onItemClick
    )
  }
}

@Composable
fun OfferList(
    headers: List<String> = emptyList(),
    selectedCategory: String = "Pizza",
    products: List<ItemDetail> = emptyList(),
    onTabClick: (String) -> Unit = {},
    onItemClick: (ItemDetail) -> Unit = {}) {

    Column() {

        TabHeaders(
            selectedTab = selectedCategory,
            headers = headers,
            onTabClick = onTabClick)

        Spacer(modifier = Modifier.height(5.dp))

        LazyRow {
            items(items = products) { item ->
                val bitmap = ImageBitmap.imageResource(item.image)

                OfferItem(
                    bitmap = bitmap,
                    item = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
fun OfferItem(
    bitmap: ImageBitmap,
    item: ItemDetail,
    onItemClick: (ItemDetail) -> Unit = {})
{
    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .clickable { onItemClick },
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(10))
    {
        Column() {
            Row {
                Image(
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .offset(25.dp),
                    bitmap = bitmap,
                    alignment = Alignment.TopCenter,
                    contentDescription = null)

                Surface(
                    shape = RoundedCornerShape(10),
                    shadowElevation = 16.dp)
                {
                    Box(
                        modifier = Modifier.padding(10.dp),
                        contentAlignment = Alignment.TopCenter)
                    {
                        Image(
                            modifier = Modifier.size(50.dp,50.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                            // alignment = Alignment.BottomEnd,
                            colorFilter = ColorFilter.tint(Green800),
                            contentDescription = null)
                    }
                }
             }
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp),
                fontWeight = FontWeight.Bold,
                text = item.name)

            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 10.dp),
                fontWeight = FontWeight.Normal,
                text = item.price.toString())
        }
    }
}

@Composable
fun TabHeaders(
    selectedTab: String = "Pizza",
    headers: List<String>,
    onTabClick: (String) -> Unit = {})
{
    LazyRow {
        items(items = headers) { header ->

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .clickable { onTabClick(header) },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = if(selectedTab == header) Color.Black
                        else Color.Gray,
                text = header)

        }
    }

}

@Composable
fun PromotionAdds() {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        color = Neutral900,
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(10)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth())
        {
            Column(modifier = Modifier.padding(16.dp))
            {
                Text(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = "-20% zniżki")

                Text(
                    color = Color.White,
                    text = "Pizza wegetarianska")

                IconButton(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 10.dp, bottom = 10.dp)
                        .border(
                            border = BorderStroke(1.dp, color = Color.LightGray),
                            shape = RoundedCornerShape(10)
                        ),
                    onClick = { /*TODO*/ })
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                        tint = Color.White,
                        contentDescription = null)
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd)
            {
                Image(
                    modifier = Modifier.size(150.dp),
                    bitmap = ImageBitmap.imageResource(R.drawable.pizza_three),
                    contentDescription = null)
            }
        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun SearchField(
    text: String,
    onSearch: (String) -> Unit)
{
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth(),
        label = { Text(text = "Wyszukaj dane") },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = null) },
        value = text,
        onValueChange = { onSearch(it) })
}

@Composable
fun WelcomeText(name: String = "") {

    Column(modifier = Modifier
        .padding(
            top = 30.dp,
            start = 16.dp,
            end = 20.dp
        )
        .fillMaxWidth())
    {
        Text(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            text = "Cześć $name,\r\nna co masz dzisiaj ochotę?")
    }
}

@Composable
fun HomeHeader(
    address: String = "Kozielska 5a",
    onProfileClick: () -> Unit = {})
{
    var isExpanded by remember { mutableStateOf(false) }
    var arrowIcon = when(isExpanded) {
        true -> ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
        false -> ImageVector.vectorResource(id = R.drawable.ic_arrow_up)
    }

    Row(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center)
    {
        Column() {
            //wiersz nasłuchujacy zdarzenia:
            Row(modifier = Modifier.clickable { isExpanded = !isExpanded })
            {
                Text(
                    color = Color.Gray,
                    text = "Twój adres"
                )
                Icon(
                    modifier = Modifier.padding(start = 10.dp),
                    imageVector = arrowIcon,
                    contentDescription = null
                )
            }
                if(isExpanded) {
                    Text(
                        text = address,
                        fontWeight = FontWeight.Bold)
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd)
        {
            val profile_image = painterResource(id = R.drawable.avatar_0)

            Image(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(48.dp)
                    .clickable { onProfileClick },
                painter = profile_image,
                contentDescription = "profile image"
                )


        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}
/*@Preview(showBackground = true)
@Composable
fun WelcomeTextPreview() {
    WelcomeText(name = "Zuza")
} */
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        data = sampleHomeData,
        onItemClick = { /*TODO*/ },
        onProfileClick = { /*TODO*/ },
        onSearch = {} )
}

