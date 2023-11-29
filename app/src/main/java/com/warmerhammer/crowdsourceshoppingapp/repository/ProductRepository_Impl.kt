package com.warmerhammer.crowdsourceshoppingapp.repository

import com.warmerhammer.crowdsourceshoppingapp.domain.model.Product
import com.warmerhammer.crowdsourceshoppingapp.network.APIServices
import com.warmerhammer.crowdsourceshoppingapp.network.model.ProductDtoMapper

class ProductRepository_Impl(
    private val apiService: APIServices,
    private val mapper: ProductDtoMapper
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return mapper.toDomainList(apiService.getAllProducts())
    }

//    override suspend fun getProductID(id: Int): Product {
//        return mapper.toDomainList(apiService.getProductID(id))
//    }
}