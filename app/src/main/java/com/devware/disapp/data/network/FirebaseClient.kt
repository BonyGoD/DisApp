package com.devware.disapp.data.network

import com.devware.disapp.data.model.ProductoInfo

interface FirebaseClient {

    suspend fun getProductos(): List<ProductoInfo>

    suspend fun getMinVersion(): List<Int>
}