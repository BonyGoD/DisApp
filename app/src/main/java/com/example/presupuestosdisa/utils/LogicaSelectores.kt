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
import com.example.presupuestosdisa.data.model.MedidasState
import com.example.presupuestosdisa.data.model.Producto
import com.example.presupuestosdisa.ui.view.componentes.CheckBoxComponent
import com.example.presupuestosdisa.ui.view.componentes.DropDownComponent
import com.example.presupuestosdisa.ui.view.componentes.ImageComponent
import com.example.presupuestosdisa.ui.view.componentes.TextFieldComponent
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel

private val ITEMS_VENTANA: String = "itemsTipoVentana"
private val ITEMS_VIDRIO: String = "itemsTipoVidrio"
private val ITEMS_PERSIANA: String = "itemsTipoPersiana"
private val ITEMS_REGISTRO: String = "itemsTipoRegistro"
private val ITEMS_SERIE: String = "itemsTipoSerie"
private val ITEMS_COLORES: String = "itemsColores"

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
    tipoVentana: String,
    productosList: MutableList<Producto>,
    fireBaseViewModel: FireBaseViewModel
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
                DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_VENTANA].orEmpty(), selectedTipoVentana, productosList, "Tipo")
                when (tipoVentana) {
                    "Practicable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckBoxComponent(nombreMenu, checkboxStateVentana, productosList)
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_SERIE].orEmpty(), selectedTipoSerie, productosList, "Serie")
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectedTipoColorVentana, productosList, "Color")
                        }
                    }

                    "Corredera" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_SERIE].orEmpty(), selectedTipoSerie, productosList, "Serie")
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectedTipoColorVentana, productosList, "Color")
                        }
                    }
                    "Elevable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectedTipoColorVentana, productosList, "Color")
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
                        TextFieldComponent(nombreMenu, "Ancho", medidas.valorAncho, productosList)
                        TextFieldComponent(nombreMenu, "Alto", medidas.valorAlto, productosList)
                    }
                }
                ImageComponent() {
                    checkboxStateVentana.value = false
                    resetDropdown(selectedTipoSerie, "Serie")
                    resetDropdown(selectedTipoColorVentana, "Color")
                    resetDropdown(selectedTipoVentana, "Tipo de Ventana")
                    resetMedidas(nombreMenu, medidasState)
                    deleteProducto(productosList, nombreMenu)
                }
            }

            "Vidrio" -> {
                DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_VIDRIO].orEmpty(), selectedTipoVidrio, productosList, "Tipo")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu,"Ancho", medidas.valorAncho, productosList)
                        TextFieldComponent(nombreMenu,"Alto", medidas.valorAlto, productosList)
                    }
                }
                ImageComponent() {
                    resetDropdown(selectedTipoVidrio, "Tipo de Vidrio")
                    resetMedidas(nombreMenu, medidasState)
                    deleteProducto(productosList, nombreMenu)
                }
            }

            "Persiana" -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_PERSIANA].orEmpty(), selectedTipoPersiana, productosList, "Tipo")
                    CheckBoxComponent(nombreMenu, checkBoxStatePersiana, productosList)
                    DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(), selectedTipoColorPersiana, productosList, "Color")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu,"Ancho", medidas.valorAncho, productosList)
                        TextFieldComponent(nombreMenu,"Alto", medidas.valorAlto, productosList)

                    }
                }
                ImageComponent() {
                    resetDropdown(selectedTipoPersiana, "Tipo de Persiana")
                    checkBoxStatePersiana.value = false
                    resetDropdown(selectedTipoColorPersiana, "Color")
                    resetMedidas(nombreMenu, medidasState)
                    deleteProducto(productosList, nombreMenu)
                }
            }

            "Registro" -> {
                DropDownComponent(nombreMenu, getItems(fireBaseViewModel)[ITEMS_REGISTRO].orEmpty(), selectedTipoRegistro, productosList, "Tipo")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    medidasState.filter { it.tipo == nombreMenu }.forEach { medidas ->
                        TextFieldComponent(nombreMenu,"Ancho", medidas.valorAncho, productosList)
                        TextFieldComponent(nombreMenu,"Alto", medidas.valorAlto, productosList)

                    }
                }
                ImageComponent() {
                    resetDropdown(selectedTipoRegistro, "Tipo de Registro")
                    resetMedidas(nombreMenu, medidasState)
                    deleteProducto(productosList, nombreMenu)
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

fun deleteProducto(productosList: MutableList<Producto>, nombreMenu: String) {
    productosList.removeIf { producto -> producto.nombre == nombreMenu }
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