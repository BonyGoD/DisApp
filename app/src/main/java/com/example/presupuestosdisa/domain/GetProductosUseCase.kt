package com.example.presupuestosdisa.domain

import com.example.presupuestosdisa.data.model.ProductoInfo
import com.example.presupuestosdisa.data.repositories.ProductoRepository
import javax.inject.Inject

class GetProductosUseCase @Inject constructor(
    private val repository: ProductoRepository
) {
    suspend operator fun invoke(): List<ProductoInfo> = repository.getAllProductos()

}