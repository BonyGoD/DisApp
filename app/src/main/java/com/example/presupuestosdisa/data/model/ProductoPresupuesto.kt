package com.example.presupuestosdisa.data.model

data class ProductoPresupuesto(
    val nombreProducto: String? = "",
    val motorizada: Boolean? = false,
    val serie: Serie? = null,
    val tipo: Tipo? = null
)
