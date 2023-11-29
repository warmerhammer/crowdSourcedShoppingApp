package com.warmerhammer.crowdsourceshoppingapp.network.model

import com.warmerhammer.crowdsourceshoppingapp.domain.DomainMapper
import com.warmerhammer.crowdsourceshoppingapp.domain.model.Product

class ProductDtoMapper : DomainMapper<ProductDto, Product> {
    override fun mapToDomainModel(model: ProductDto): Product {
        return Product(
            id = model.id,
            product_name = model.product_name,
            category = model.category,
            description = model.description,
            image = model.image,
            upvotes = model.upvotes,
            tags = model.tags.orEmpty(),
        )
    }

    override fun mapFromDomainModel(domainModel: Product): ProductDto {
        return ProductDto(
            id = domainModel.id,
            product_name = domainModel.product_name,
            category = domainModel.category,
            description = domainModel.description,
            image = domainModel.image,
            upvotes = domainModel.upvotes,
            tags = domainModel.tags.orEmpty(),
        )
    }

    fun toDomainList(initial: List<ProductDto>): List<Product>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Product>): List<ProductDto>{
        return initial.map { mapFromDomainModel(it) }
    }


}