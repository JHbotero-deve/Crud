package com.sena.crud.data.remote.api

import com.sena.crud.data.remote.dto.req.product.Product
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.DELETE

interface ProductApiService {
    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @retrofit2.http.Body product: Product
    ): Product
    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int
    ): Product
}