package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.core.FirebaseHelper
import com.example.presupuestosdisa.data.model.ProductoInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductosService {

    private val firebaseClient: FirebaseClient = FirebaseClientImpl(FirebaseHelper.getFirebase())

    suspend fun getProductos(): List<ProductoInfo> {
        return withContext(Dispatchers.IO) {
            firebaseClient.getProductos()
        }
    }
}