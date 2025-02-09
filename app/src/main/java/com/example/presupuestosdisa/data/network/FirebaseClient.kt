package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.data.model.ProductoInfo
import javax.inject.Inject

interface FirebaseClient {

    suspend fun getProductos(): List<ProductoInfo>
}