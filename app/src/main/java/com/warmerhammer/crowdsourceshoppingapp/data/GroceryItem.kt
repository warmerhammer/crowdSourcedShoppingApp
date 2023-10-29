package com.warmerhammer.crowdsourceshoppingapp.data

data class GroceryItem(
    val name: String,
    val description: String,
    val price: Double,
    val image: Int,
    val category: String,
    val id: Int
)
