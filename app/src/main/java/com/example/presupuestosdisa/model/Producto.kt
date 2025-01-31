package com.example.presupuestosdisa.model

data class Producto(
    var nombre: String = "",
    var tipoVentana: String = "",
    var tipoVidrio: String = "",
    var tipoPersiana: String = "",
    var tipoRegistro: String = "",
    var tipoSerie: String = "",
    var tipoColor: String = "",
    var motorizada: Boolean = false,
    var oscilobatiente: Boolean = false,
    var ancho: Long = 0,
    var alto: Long = 0
)
