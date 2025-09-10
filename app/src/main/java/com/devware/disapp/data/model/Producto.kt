package com.devware.disapp.data.model

import com.devware.disapp.data.model.ConstantesTipos.PERSIANA
import com.devware.disapp.data.model.ConstantesTipos.REGISTRO
import com.devware.disapp.data.model.ConstantesTipos.VENTANA
import com.devware.disapp.data.model.ConstantesTipos.VIDRIO

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

        return when(nombre){
            VENTANA -> tipo.isNotBlank() && tipoSerie.isNotBlank() && tipoColor.isNotBlank() && ancho > 0 && alto > 0
            VIDRIO -> tipo.isNotBlank() && ancho > 0 && alto > 0
            REGISTRO -> tipo.isNotBlank() && ancho > 0 && alto > 0
            PERSIANA -> tipo.isNotBlank() && tipoColor.isNotBlank() && ancho > 0 && alto > 0
            else -> false
        }
    }

}
