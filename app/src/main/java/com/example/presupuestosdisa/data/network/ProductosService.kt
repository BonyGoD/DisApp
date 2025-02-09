package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.data.model.ProductoInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductosService @Inject constructor(
    private val firebaseClient: FirebaseClient
) {
    suspend fun getProductos(): List<ProductoInfo> {
        return withContext(Dispatchers.IO) {
            firebaseClient.getProductos()
        }
    }
}