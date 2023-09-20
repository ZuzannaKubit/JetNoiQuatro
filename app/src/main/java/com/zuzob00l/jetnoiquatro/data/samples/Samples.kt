package com.zuzob00l.jetnoiquatro.data.samples

import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.data.ItemDetail
import com.zuzob00l.jetnoiquatro.data.Order
import com.zuzob00l.jetnoiquatro.data.UIState
import com.zuzob00l.jetnoiquatro.data.UserData


val sampleUserOne = UserData(1L, "Jan", "Kowalski", "Krakowiaków 13a", email = "1@wp.pl", password = "1234")
val sampleUserTwo = UserData(2L, "Kuba", "Nowak", "Wola 13a", email = "2@wp.pl", password = "1234")

val sampleItemDetailZero = ItemDetail(
    id = 0L,
    orderState = "Dostarczone",
    name = "Trzy sery",
    date = "5 Czerwca 2022",
    ingredients = "Mozarella, gran padano, gouda",
    price = 100.99f,
    image = R.drawable.pizza_two
)

val sampleItemDetailOne = ItemDetail(
    id = 1L,
    orderState = "Dostarczone",
    name = "Ser i pomidory",
    date = "5 Czerwca 2022",
    ingredients = "Ser i pomidor, podwójne pepperoni, meksykańska x2",
    price = 91.23f,
    image = R.drawable.pizza_one
)

val sampleItemDetailTwo = ItemDetail(
    id = 2L,
    orderState = "Dostarczone",
    name = "Pepperoni z kiełbasą ",
    date = "5 Czerwca 2022",
    ingredients = "Ser i pomidor, podwójne pepperoni, meksykańska x2",
    image = R.drawable.pizza_two,
    price = 35.99f
)

val sampleOrdersOne = buildList {
    for (i in 0..10) {
        val order = Order(item = sampleItemDetailOne.copy(id = i.toLong(), price = i.toFloat()), i)
        add(order)
    }
}.toList()

val sampleOrdersTwo = buildList {
    for (i in 0..10) {
        add(sampleItemDetailTwo.copy(id = i.toLong()))
    }
}.toList()

val sampleHeader = listOf("Pizza", "Sałatki", "Wegańskie", "Desery")

val sampleHomeData = UIState.Home(
    products = sampleOrdersTwo,
    userData = sampleUserOne
)

val sampleOrderHistoryData =
    UIState.OrderHistory(orderList = sampleOrdersOne)

val sampleEmptyOrderHistoryData =
    UIState.OrderHistory(orderList = emptyList())


val sampleItemDetailScreen =
    UIState.ItemDetailScreen(item = sampleOrdersOne[0].item)

val sampleProfile = UIState.Profile(name = "Jan", surname = "Kowalski", email = "jan.kowalski@w.pl")

val sampleShoppingBag = UIState.ShoppingBag(itemList = sampleOrdersOne)

val samplePayment = UIState.Payment(userData = sampleUserOne, orderList = sampleOrdersOne)

val sampleMapData = UIState.Map(
    name = "Kuba",
    surname = "Kowalski",
    sourceAddress = "Krakowiaków 13a",
    targetAddress = "Głodomorska 12")

