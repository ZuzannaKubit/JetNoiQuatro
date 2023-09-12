package pl.strefakursow.noiquattro.data

import androidx.annotation.DrawableRes
import pl.strefakursow.noiquattro.R

data class ItemDetail(
    val id: Long,
    val orderState: String,
    val name: String,
    val date: String,
    val ingredients: String,
    val calories: String = "Calories: 200kcal/100g",
    val price: Float,
    @DrawableRes val image: Int = R.drawable.pizza_one,
    val hashTags: List<String> = listOf("Wegańskie", "Ostre", "Bardzo ostre")
)
