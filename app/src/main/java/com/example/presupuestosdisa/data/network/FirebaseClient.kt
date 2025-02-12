package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.data.model.ProductoInfo

interface FirebaseClient {

    suspend fun getProductos(): List<ProductoInfo>

    suspend fun getMinVersion(): List<Int>
}