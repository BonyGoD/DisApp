package com.devware.disapp.data.network

import com.devware.disapp.data.model.ProductoInfo
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