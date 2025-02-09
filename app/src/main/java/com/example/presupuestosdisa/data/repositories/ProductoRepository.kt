package com.example.presupuestosdisa.data.repositories

import com.example.presupuestosdisa.data.model.ProductoInfo
import com.example.presupuestosdisa.data.network.ProductosService
import javax.inject.Inject

class ProductoRepository @Inject constructor(
    private val firebaseService: ProductosService
) {

    suspend fun getAllProductos():List<ProductoInfo> {
        val response: List<ProductoInfo> = firebaseService.getProductos()
        return response
    }
}