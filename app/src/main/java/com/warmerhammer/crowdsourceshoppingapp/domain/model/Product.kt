package com.warmerhammer.crowdsourceshoppingapp.domain.model

data class Product(
    var id: Int,
    var product_name: String,
    var category: String? = null,
    var description: String,
    var image: String,
    var upvotes: Int,
    var tags: List<String>? = mutableListOf(),
)