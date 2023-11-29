package com.warmerhammer.crowdsourceshoppingapp.network.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("product_name")
    var product_name: String,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("upvotes")
    var upvotes: Int,
    @SerializedName("tags")
    var tags: List<String>? = mutableListOf(),


    )