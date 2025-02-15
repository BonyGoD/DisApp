package com.example.presupuestosdisa.utils

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.data.model.MedidasState
import com.example.presupuestosdisa.data.model.SelectablesPresupuestos
import com.example.presupuestosdisa.ui.view.componentes.CheckBoxComponent
import com.example.presupuestosdisa.ui.view.componentes.DropDownComponent
import com.example.presupuestosdisa.ui.view.componentes.ImageComponent
import com.example.presupuestosdisa.ui.view.componentes.TextFieldComponent
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel

private const val ITEMS_VENTANA: String = "itemsTipoVentana"
private const val ITEMS_VIDRIO: String = "itemsTipoVidrio"
private const val ITEMS_PERSIANA: String = "itemsTipoPersiana"
private const val ITEMS_REGISTRO: String = "itemsTipoRegistro"
private const val ITEMS_SERIE: String = "itemsTipoSerie"
private const val ITEMS_COLORES: String = "itemsColores"

@Composable
fun LogicaSelectores(
    nombreMenu: String,
    selectablesPresupuestos: SelectablesPresupuestos,
    tipoVentana: String,
    fireBaseViewModel: FireBaseViewModel
) {


    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (nombreMenu) {
            "Ventana" -> {
                DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_VENTANA].orEmpty(), selectablesPresupuestos.selectedTipoVentana, "Tipo")
                when (tipoVentana) {
                    "Practicable" -> {
                        Row(
                            modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckBoxComponent(nombreMenu, selectablesPresupuestos.checkboxStateVentana)
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_SERIE].orEmpty(), selectablesPresupuestos.selectedTipoSerie, "Serie")
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectablesPresupuestos.selectedColorVentana, "Color")
                        }
                    }

                    "Corredera" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_SERIE].orEmpty(), selectablesPresupuestos.selectedTipoSerie, "Serie")
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectablesPresupuestos.selectedColorVentana, "Color")
                        }
                    }
                    "Elevable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectablesPresupuestos.selectedColorVentana, "Color")
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu, "Ancho", medidas.valorAncho)
                        Spacer(modifier = Modifier.padding(10.dp))
                        TextFieldComponent(nombreMenu, "Alto", medidas.valorAlto)
                    }
                }
                ImageComponent() {
                    selectablesPresupuestos.checkboxStateVentana.value = false
                    resetDropdown(selectablesPresupuestos.selectedTipoSerie, "Serie")
                    resetDropdown(selectablesPresupuestos.selectedColorVentana, "Color")
                    resetDropdown(selectablesPresupuestos.selectedTipoVentana, "Tipo de Ventana")
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
                }
            }

            "Vidrio" -> {
                DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_VIDRIO].orEmpty(), selectablesPresupuestos.selectedTipoVidrio, "Tipo")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu,"Ancho", medidas.valorAncho)
                        Spacer(modifier = Modifier.padding(10.dp))
                        TextFieldComponent(nombreMenu,"Alto", medidas.valorAlto)
                    }
                }
                ImageComponent() {
                    resetDropdown(selectablesPresupuestos.selectedTipoVidrio, "Tipo de Vidrio")
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
                }
            }

            "Persiana" -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_PERSIANA].orEmpty(), selectablesPresupuestos.selectedTipoPersiana, "Tipo")
                    CheckBoxComponent(nombreMenu, selectablesPresupuestos.checkboxStatePersiana)
                    DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectablesPresupuestos.selectedColorPersiana, "Color")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu,"Ancho", medidas.valorAncho)
                        Spacer(modifier = Modifier.padding(10.dp))
                        TextFieldComponent(nombreMenu,"Alto", medidas.valorAlto)

                    }
                }
                ImageComponent() {
                    resetDropdown(selectablesPresupuestos.selectedTipoPersiana, "Tipo de Persiana")
                    selectablesPresupuestos.checkboxStatePersiana.value = false
                    resetDropdown(selectablesPresupuestos.selectedColorPersiana, "Color")
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
                }
            }

            "Registro" -> {
                DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_REGISTRO].orEmpty(), selectablesPresupuestos.selectedTipoRegistro, "Tipo")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu,"Ancho", medidas.valorAncho)
                        Spacer(modifier = Modifier.padding(10.dp))
                        TextFieldComponent(nombreMenu,"Alto", medidas.valorAlto)

                    }
                }
                ImageComponent() {
                    resetDropdown(selectablesPresupuestos.selectedTipoRegistro, "Tipo de Registro")
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
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

fun deleteProducto(nombreMenu: String) {
    LogicaDropdown().getProductList().removeIf { producto -> producto.nombre == nombreMenu }
}

fun getItems(fireBaseViewModel: FireBaseViewModel): Map<String, List<String?>> {

    val itemsTipoVentana = fireBaseViewModel.producto.value
        ?.filter { it.nombre == "Ventana" }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoVidrio = fireBaseViewModel.producto.value
        ?.filter { it.nombre == "Vidrio" }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoPersiana = fireBaseViewModel.producto.value
        ?.filter { it.nombre == "Persiana" }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoRegistro = fireBaseViewModel.producto.value
        ?.filter { it.nombre == "Registro" }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoSerie = fireBaseViewModel.producto.value
        ?.filter { it.nombre == "Ventana" }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.flatMap { it.serie ?: emptyList() }
        ?.map { it.nombre }
        ?.distinct()
        ?.toMutableList() ?: mutableListOf()

    val itemsColores = fireBaseViewModel.producto.value
        ?.flatMap { it.colores ?: emptyList() }
        ?.map { it.nombre }
        ?.toMutableList() ?: mutableListOf()

    return mapOf(
        "itemsTipoVentana" to itemsTipoVentana,
        "itemsTipoVidrio" to itemsTipoVidrio,
        "itemsTipoPersiana" to itemsTipoPersiana,
        "itemsTipoRegistro" to itemsTipoRegistro,
        "itemsTipoSerie" to itemsTipoSerie,
        "itemsColores" to itemsColores
    )
}