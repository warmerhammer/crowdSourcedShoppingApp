package com.warmerhammer.crowdsourceshoppingapp.data

data class GroceryItem(
    val name: String,
    val description: String,
    var price : Float = 0.0f,
    val image: String,
    val category: String? = null,
    val id: Long = System.currentTimeMillis(),
    var upvotes: Int = 0,
    var tags : MutableList<String> = mutableListOf(),
    var store : String = "Unknown"
)
