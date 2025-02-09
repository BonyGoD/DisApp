package com.example.presupuestosdisa.data.model

data class Tipo(
    val tipo: String? = "",
    val precio: Int? = 0,
    val colores: List<Colores>? = null,
    var serie: List<Serie>? = null,
    val oscilobatiente: Boolean? = false,
)
