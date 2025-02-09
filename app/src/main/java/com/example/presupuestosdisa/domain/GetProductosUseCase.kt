package com.example.presupuestosdisa.domain

import com.example.presupuestosdisa.data.ProductoRepository
import com.example.presupuestosdisa.data.model.ProductoInfo

class GetProductosUseCase {

    private val repository = ProductoRepository()

    suspend operator fun invoke(): List<ProductoInfo>? = repository.getAllProductos()

}