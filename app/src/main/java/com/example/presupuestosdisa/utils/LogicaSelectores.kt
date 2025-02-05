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
import androidx.lifecycle.ViewModel
import com.example.presupuestosdisa.componentes.CheckBoxComponent
import com.example.presupuestosdisa.componentes.DropDownComponent
import com.example.presupuestosdisa.componentes.ImageComponent
import com.example.presupuestosdisa.componentes.TextFieldComponent
import com.example.presupuestosdisa.model.MedidasState
import com.example.presupuestosdisa.model.Producto
import com.example.presupuestosdisa.viewModel.ProductoMenuViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

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
    productosList: MutableList<Producto>
) {

    val productoMenuViewModel: ProductoMenuViewModel = viewModel()

    val itemsTipoVentana = productoMenuViewModel.tipoVentana.value.map { it.tipo }
    val itemsTipoVidrio = productoMenuViewModel.tipoVidrio.value.map { it.tipo }
    val itemsTipoPersiana = productoMenuViewModel.tipoPersiana.value.map { it.tipo }
    val itemsTipoRegistro = productoMenuViewModel.tipoRegistro.value.map { it.tipo }
    val itemsTipoSerie = productoMenuViewModel.tipoSerie.value.map { it.nombre }
    val itemsColores = productoMenuViewModel.colores.value.map { it.nombre }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (nombreMenu) {
            "Ventana" -> {
                DropDownComponent(nombreMenu, itemsTipoVentana, selectedTipoVentana, productosList, "Tipo")
                when (tipoVentana) {
                    "Practicable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckBoxComponent(nombreMenu, checkboxStateVentana, productosList)
                            DropDownComponent(nombreMenu, itemsTipoSerie, selectedTipoSerie, productosList, "Serie")
                            DropDownComponent(nombreMenu, itemsColores, selectedTipoColorVentana, productosList, "Color")
                        }
                    }

                    "Corredera" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(nombreMenu, itemsTipoSerie, selectedTipoSerie, productosList, "Serie")
                            DropDownComponent(nombreMenu, itemsColores, selectedTipoColorVentana, productosList, "Color")
                        }
                    }
                    "Elevable" -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(nombreMenu, itemsColores, selectedTipoColorVentana, productosList, "Color")
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
                DropDownComponent(nombreMenu, itemsTipoVidrio, selectedTipoVidrio, productosList, "Tipo")
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
                    DropDownComponent(nombreMenu, itemsTipoPersiana, selectedTipoPersiana, productosList, "Tipo")
                    CheckBoxComponent(nombreMenu, checkBoxStatePersiana, productosList)
                    DropDownComponent(nombreMenu, itemsColores, selectedTipoColorPersiana, productosList, "Color")
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
                DropDownComponent(nombreMenu, itemsTipoRegistro, selectedTipoRegistro, productosList, "Tipo")
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