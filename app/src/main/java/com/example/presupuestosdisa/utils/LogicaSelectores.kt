package com.example.presupuestosdisa.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.R
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
    selectedTipoColorVentana: MutableState<String>,
    selectedTipoColorPersiana: MutableState<String>,
    checkboxStateVentana: MutableState<Boolean>,
    checkBoxStatePersiana: MutableState<Boolean>,
    tipoVentana: String
) {

    when(nombreMenu){
        "Ventana" -> {
            DropDown(itemsTipoVentana, selectedTipoVentana)
            when(tipoVentana) {
                "Practicable" -> {
                    CheckBox(nombreMenu, checkboxStateVentana)
                    DropDown(itemsTipoSerie, selectedTipoSerie)
                    DropDown(itemsColores, selectedTipoColorVentana)
                    Image(
                        painter = painterResource(R.drawable.basura),
                        contentDescription = "Basura",
                        modifier = Modifier
                            .clickable {
                                checkboxStateVentana.value = false
                                resetDropdown(selectedTipoSerie, "Serie")
                                resetDropdown(selectedTipoColorVentana, "Color")
                                resetDropdown(selectedTipoVentana, "Tipo de Ventana")
                            }
                            .size(20.dp)
                    )
                }
                "Corredera" -> {
                    DropDown(itemsTipoSerie, selectedTipoSerie)
                    DropDown(itemsColores, selectedTipoColorVentana)
                    Image(
                        painter = painterResource(R.drawable.basura),
                        contentDescription = "Basura",
                        modifier = Modifier
                            .clickable {
                                resetDropdown(selectedTipoSerie, "Serie")
                                resetDropdown(selectedTipoColorVentana, "Color")
                                resetDropdown(selectedTipoVentana, "Tipo de Ventana")
                            }
                            .size(20.dp)
                    )
                }
                "Elevable" -> {
                    DropDown(itemsColores, selectedTipoColorVentana)
                    Image(
                        painter = painterResource(R.drawable.basura),
                        contentDescription = "Basura",
                        modifier = Modifier
                            .clickable {
                                resetDropdown(selectedTipoColorVentana, "Color")
                                resetDropdown(selectedTipoVentana, "Tipo de Ventana")
                            }
                            .size(20.dp)
                    )
                }
            }
        }
        "Vidrio" -> {
            DropDown(itemsTipoVidrio, selectedTipoVidrio)
            Image(
                painter = painterResource(R.drawable.basura),
                contentDescription = "Basura",
                modifier = Modifier
                    .clickable {
                        resetDropdown(selectedTipoVidrio, "Tipo de Vidrio")
                    }
                    .size(20.dp)
            )
        }
        "Persiana" -> {
            DropDown(itemsTipoPersiana, selectedTipoPersiana)
            CheckBox(nombreMenu, checkBoxStatePersiana)
            DropDown(itemsColores, selectedTipoColorPersiana)
            Image(
                painter = painterResource(R.drawable.basura),
                contentDescription = "Basura",
                modifier = Modifier
                    .clickable {
                        resetDropdown(selectedTipoPersiana, "Tipo de Persiana")
                        checkBoxStatePersiana.value = false
                        resetDropdown(selectedTipoColorPersiana, "Color")
                    }
                    .size(20.dp)
            )
        }
        "Registro" -> {
            DropDown(itemsTipoRegistro, selectedTipoRegistro)
            Image(
                painter = painterResource(R.drawable.basura),
                contentDescription = "Basura",
                modifier = Modifier
                    .clickable {
                        resetDropdown(selectedTipoRegistro, "Tipo de Registro")
                    }
                    .size(20.dp)
            )
        }
        }
    }
fun resetDropdown(selectedItem: MutableState<String>, initialValue: String) {
    selectedItem.value = initialValue
}