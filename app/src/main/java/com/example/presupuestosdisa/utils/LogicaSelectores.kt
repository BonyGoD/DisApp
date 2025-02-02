package com.example.presupuestosdisa.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.componentes.CheckBoxComponent
import com.example.presupuestosdisa.componentes.DropDownComponent
import com.example.presupuestosdisa.componentes.ImageComponent
import com.example.presupuestosdisa.componentes.TextFieldComponent
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
    medidasState: List<MedidasState>,
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent("Ancho", medidas.valorAncho)
                        TextFieldComponent("Alto", medidas.valorAlto)
                    }
                }
                ImageComponent() {
                    checkboxStateVentana.value = false
                    resetDropdown(selectedTipoSerie, "Serie")
                    resetDropdown(selectedTipoColorVentana, "Color")
                    resetDropdown(selectedTipoVentana, "Tipo de Ventana")
                    resetMedidas(nombreMenu, medidasState)
                }
            }

            "Vidrio" -> {
                DropDownComponent(itemsTipoVidrio, selectedTipoVidrio)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent("Ancho", medidas.valorAncho)
                        TextFieldComponent("Alto", medidas.valorAlto)
                    }
                }
                ImageComponent() {
                    resetDropdown(selectedTipoVidrio, "Tipo de Vidrio")
                    resetMedidas(nombreMenu, medidasState)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent("Ancho", medidas.valorAncho)
                        TextFieldComponent("Alto", medidas.valorAlto)

                    }
                }
                ImageComponent() {
                    resetDropdown(selectedTipoPersiana, "Tipo de Persiana")
                    checkBoxStatePersiana.value = false
                    resetDropdown(selectedTipoColorPersiana, "Color")
                    resetMedidas(nombreMenu, medidasState)
                }
            }

            "Registro" -> {
                DropDownComponent(itemsTipoRegistro, selectedTipoRegistro)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent("Ancho", medidas.valorAncho)
                        TextFieldComponent("Alto", medidas.valorAlto)

                    }
                }
                ImageComponent() {
                    resetDropdown(selectedTipoRegistro, "Tipo de Registro")
                    resetMedidas(nombreMenu, medidasState)
                }
            }
        }
    }
}

fun resetDropdown(selectedItem: MutableState<String>, initialValue: String) {
    selectedItem.value = initialValue
}

fun resetMedidas(nombreMenu: String, medidasState: List<MedidasState>) {
    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
        medidas.valorAncho.value = ""
        medidas.valorAlto.value = ""
    }
}