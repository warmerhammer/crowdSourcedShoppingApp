package com.warmerhammer.crowdsourceshoppingapp.repository

import com.warmerhammer.crowdsourceshoppingapp.domain.model.Product

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>

//    suspend fun getProductID(id: Int): Product
}