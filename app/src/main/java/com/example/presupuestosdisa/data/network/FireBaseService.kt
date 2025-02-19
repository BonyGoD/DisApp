package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.data.model.ProductoInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FireBaseService @Inject constructor(
    private val firebaseClient: FirebaseClient
) {
    suspend fun getProductos(): List<ProductoInfo> {
        return withContext(Dispatchers.IO) {
            firebaseClient.getProductos()
        }
    }
    suspend fun getMinVersion(): List<Int> {
        return withContext(Dispatchers.IO) {
            firebaseClient.getMinVersion()
        }
    }
}