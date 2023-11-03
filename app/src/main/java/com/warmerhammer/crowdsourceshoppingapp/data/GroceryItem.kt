package com.warmerhammer.crowdsourceshoppingapp.data

data class GroceryItem(
    val name: String,
    val description: String,
    var price : Float = 0.0f,
    val image: String,
    val category: String? = null,
    val id: Int = (0..100000).random()
)
