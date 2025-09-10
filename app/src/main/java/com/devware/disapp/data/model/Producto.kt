package com.devware.disapp.data.model

data class Producto(
    var nombre: String = "",
    var tipo: String = "",
    var tipoSerie: String = "",
    var tipoColor: String = "",
    var oscilobatiente: Boolean = false,
    var motorizada: Boolean = false,
    var ancho: Long = 0,
    var alto: Long = 0,
) {
    fun esValido(): Boolean {
        return tipo.isNotBlank() &&
                tipoSerie.isNotBlank() &&
                tipoColor.isNotBlank() &&
                ancho > 0 &&
                alto > 0
    }
}
