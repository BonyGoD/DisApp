package com.example.presupuestosdisa.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.presupuestosdisa.componentes.CheckBox
import com.example.presupuestosdisa.componentes.DropDown
import com.example.presupuestosdisa.pantallas.itemsColores
import com.example.presupuestosdisa.pantallas.itemsTipoPersiana
import com.example.presupuestosdisa.pantallas.itemsTipoRegistro
import com.example.presupuestosdisa.pantallas.itemsTipoSerie
import com.example.presupuestosdisa.pantallas.itemsTipoVentana
import com.example.presupuestosdisa.pantallas.itemsTipoVidrio

@Composable
fun LogicaSelectores(
    nombreMenu: String,
    selectedTipoVentana: MutableState<String>,
    selectedTipoVidrio: MutableState<String>,
    selectedTipoPersiana: MutableState<String>,
    selectedTipoRegistro: MutableState<String>,
    selectedTipoSerie: MutableState<String>,
    selectedTipoColor: MutableState<String>,
    tipoVentana: String
) {

    when(nombreMenu){
        "Ventana" -> {
            DropDown(itemsTipoVentana, selectedTipoVentana)
            when(tipoVentana) {
                "Practicable" -> {
                    CheckBox(nombreMenu)
                    DropDown(itemsTipoSerie, selectedTipoSerie)
                    DropDown(itemsColores, selectedTipoColor)
                }
                "Corredera" -> {
                    DropDown(itemsTipoSerie, selectedTipoSerie)
                    DropDown(itemsColores, selectedTipoColor)
                }
                "Elevable" -> {
                    DropDown(itemsColores, selectedTipoColor)
                }
            }
        }
        "Vidrio" -> {
            DropDown(itemsTipoVidrio, selectedTipoVidrio)
        }
        "Persiana" -> {
            DropDown(itemsTipoPersiana, selectedTipoPersiana)
            CheckBox(nombreMenu)
            DropDown(itemsColores, selectedTipoColor)
        }
        "Registro" -> {
            DropDown(itemsTipoRegistro, selectedTipoRegistro)
        }
    }
}