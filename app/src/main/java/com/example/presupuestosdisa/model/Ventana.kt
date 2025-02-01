package com.example.presupuestosdisa.model

data class Ventana(
    var nombre: String = "",
    var tipoVentana: String = "",
    var tipoSerie: String = "",
    var tipoColor: String = "",
    var oscilobatiente: Boolean = false,
    var ancho: Long = 0,
    var alto: Long = 0
)
