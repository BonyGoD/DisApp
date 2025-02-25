package com.devware.disapp.domain

import com.devware.disapp.data.model.ProductoInfo
import com.devware.disapp.data.repositories.ProductoRepository
import javax.inject.Inject

class GetProductosUseCase @Inject constructor(
    private val repository: ProductoRepository
) {
    suspend operator fun invoke(): List<ProductoInfo> = repository.getAllProductos()

}