package com.sena.crud.ui.state

import com.sena.crud.domain.model.ProductModel

data class ProductUIState(
    val isLoading: Boolean = false,
    val isUpdating: Boolean = false,
    val product: ProductModel? = null,
    val errorMessage: String? = null,
    val updateSuccess: Boolean = false
)

