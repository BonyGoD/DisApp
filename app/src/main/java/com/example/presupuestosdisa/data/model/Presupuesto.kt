package com.example.presupuestosdisa.data.model

data class Presupuesto(
    val nobreCliente: String? = "",
    val telefonoCliente : String? = "",
    val producto: ProductoPresupuesto? = null
)
