package com.sena.crud.domain.repository

import com.sena.crud.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProductById(
        id: Int
    ): ProductModel

    suspend fun updateProduct(
        product: ProductModel
    ): ProductModel

    suspend fun deleteProduct(id:Int):ProductModel
}