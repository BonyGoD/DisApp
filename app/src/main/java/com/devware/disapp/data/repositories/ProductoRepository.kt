package com.devware.disapp.data.repositories

import com.devware.disapp.data.model.ProductoInfo
import com.devware.disapp.data.network.FireBaseService
import javax.inject.Inject

class ProductoRepository @Inject constructor(
    private val firebaseService: FireBaseService
) {

    suspend fun getAllProductos():List<ProductoInfo> {
        val response: List<ProductoInfo> = firebaseService.getProductos()
        return response
    }
}