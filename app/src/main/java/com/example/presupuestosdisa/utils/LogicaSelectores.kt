package com.example.presupuestosdisa.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.componentes.CheckBoxComponent
import com.example.presupuestosdisa.componentes.ComponenteMedidas
import com.example.presupuestosdisa.componentes.DropDownComponent
import com.example.presupuestosdisa.componentes.ImageComponent
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (nombreMenu) {
            "Ventana" -> {
                DropDownComponent(itemsTipoVentana, selectedTipoVentana)
                when (tipoVentana) {
                    "Practicable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckBoxComponent(nombreMenu, checkboxStateVentana)
                            DropDownComponent(itemsTipoSerie, selectedTipoSerie)
                            DropDownComponent(itemsColores, selectedTipoColorVentana)
                        }
                    }
                    "Corredera" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(itemsTipoSerie, selectedTipoSerie)
                            DropDownComponent(itemsColores, selectedTipoColorVentana)
                        }
                    }
                    "Elevable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(itemsColores, selectedTipoColorVentana)
                        }
                    }
                }
                ComponenteMedidas()
                ImageComponent() {
                    checkboxStateVentana.value = false
                    resetDropdown(selectedTipoSerie, "Serie")
                    resetDropdown(selectedTipoColorVentana, "Color")
                    resetDropdown(selectedTipoVentana, "Tipo de Ventana")
                }
            }

            "Vidrio" -> {
                DropDownComponent(itemsTipoVidrio, selectedTipoVidrio)
                ComponenteMedidas()
                ImageComponent() {
                    resetDropdown(selectedTipoVidrio, "Tipo de Vidrio")
                }
            }

            "Persiana" -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropDownComponent(itemsTipoPersiana, selectedTipoPersiana)
                    CheckBoxComponent(nombreMenu, checkBoxStatePersiana)
                    DropDownComponent(itemsColores, selectedTipoColorPersiana)
                }
                ComponenteMedidas()
                ImageComponent() {
                    resetDropdown(selectedTipoPersiana, "Tipo de Persiana")
                    checkBoxStatePersiana.value = false
                    resetDropdown(selectedTipoColorPersiana, "Color")
                }
            }

            "Registro" -> {
                DropDownComponent(itemsTipoRegistro, selectedTipoRegistro)
                ImageComponent() {
                    resetDropdown(selectedTipoRegistro, "Tipo de Registro")
                }
            }
        }
    }
}

fun resetDropdown(selectedItem: MutableState<String>, initialValue: String) {
    selectedItem.value = initialValue
}