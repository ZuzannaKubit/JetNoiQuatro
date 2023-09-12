package pl.strefakursow.noiquattro

import kotlinx.coroutines.delay
import pl.strefakursow.noiquattro.data.ItemDetail
import pl.strefakursow.noiquattro.data.Order
import pl.strefakursow.noiquattro.data.UserData
import pl.strefakursow.noiquattro.data.samples.*

class SecretBackend {

    private val users = listOf(sampleUserOne, sampleUserTwo)
    private val items = listOf(sampleItemDetailZero, sampleItemDetailOne, sampleItemDetailTwo)
    private val history = arrayListOf<Order>()

    suspend fun login(email: String, password: String): UserData? {
        delay(2000)
        return users.find { it.email == email && it.password == password }
    }

    fun getItemDetail(id: Long): ItemDetail? {
        return items.find { it.id == id }
    }

    fun getAllItems(): List<ItemDetail> {
        return items
    }

    fun saveOrderInHistory(orders: Set<Order>) {
        history.addAll(orders)
    }

    fun getOrdersHistory() = history.toList()

}
