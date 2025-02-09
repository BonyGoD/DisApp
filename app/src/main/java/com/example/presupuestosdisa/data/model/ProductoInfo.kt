package com.example.presupuestosdisa.data.model

data class ProductoInfo(
    var nombre: String? = "",
    var motorizada: Boolean? = false,
    var tipo: List<Tipo>? = null,
    var colores: List<Colores>? = null
)
