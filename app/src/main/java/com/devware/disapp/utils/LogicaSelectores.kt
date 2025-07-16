package com.devware.disapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devware.disapp.R
import com.devware.disapp.data.model.ConstantesTipos.COLOR
import com.devware.disapp.data.model.ConstantesTipos.CORREDERA
import com.devware.disapp.data.model.ConstantesTipos.ELEVABLE
import com.devware.disapp.data.model.ConstantesTipos.PERSIANA
import com.devware.disapp.data.model.ConstantesTipos.PRACTICABLE
import com.devware.disapp.data.model.ConstantesTipos.REGISTRO
import com.devware.disapp.data.model.ConstantesTipos.SERIE
import com.devware.disapp.data.model.ConstantesTipos.TIPO_PERSIANA
import com.devware.disapp.data.model.ConstantesTipos.TIPO_REGISTRO
import com.devware.disapp.data.model.ConstantesTipos.TIPO_VENTANA
import com.devware.disapp.data.model.ConstantesTipos.TIPO_VIDRIO
import com.devware.disapp.data.model.ConstantesTipos.VENTANA
import com.devware.disapp.data.model.ConstantesTipos.VIDRIO
import com.devware.disapp.data.model.MedidasState
import com.devware.disapp.data.model.SelectablesPresupuestos
import com.devware.disapp.ui.theme.ButtonDisaColor
import com.devware.disapp.ui.viewModel.FireBaseViewModel
import com.example.disapp.ui.view.componentes.CheckBoxComponent
import com.example.disapp.ui.view.componentes.DropDownComponent
import com.example.disapp.ui.view.componentes.ImageComponent
import com.example.disapp.ui.view.componentes.TextFieldComponent

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
            .clip(RoundedCornerShape(bottomStart = 22.dp, bottomEnd = 22.dp))
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .background(ButtonDisaColor)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (nombreMenu) {
            VENTANA -> {
                DropDownComponent(
                    nombreMenu,
                    getItems(fireBaseViewModel)[ITEMS_VENTANA].orEmpty(),
                    selectablesPresupuestos.selectedTipoVentana,
                    stringResource(R.string.tipo)
                )
                when (tipoVentana) {
                    PRACTICABLE -> {
                        Row(
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckBoxComponent(
                                nombreMenu,
                                selectablesPresupuestos.checkboxStateVentana
                            )
                            DropDownComponent(
                                nombreMenu,
                                getItems(fireBaseViewModel)[ITEMS_SERIE].orEmpty(),
                                selectablesPresupuestos.selectedTipoSerie,
                                stringResource(R.string.serie)
                            )
                            DropDownComponent(
                                nombreMenu,
                                getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(),
                                selectablesPresupuestos.selectedColorVentana,
                                stringResource(R.string.color)
                            )
                        }
                    }

                    CORREDERA -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(
                                nombreMenu,
                                getItems(fireBaseViewModel)[ITEMS_SERIE].orEmpty(),
                                selectablesPresupuestos.selectedTipoSerie,
                                stringResource(R.string.serie)
                            )
                            DropDownComponent(
                                nombreMenu,
                                getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(),
                                selectablesPresupuestos.selectedColorVentana,
                                stringResource(R.string.color)
                            )
                        }
                    }

                    ELEVABLE -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropDownComponent(
                                nombreMenu,
                                getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(),
                                selectablesPresupuestos.selectedColorVentana,
                                stringResource(R.string.color)
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }
                        .forEach { medidas ->
                            TextFieldComponent(nombreMenu, stringResource(R.string.ancho), medidas.valorAncho)
                            Spacer(modifier = Modifier.padding(10.dp))
                            TextFieldComponent(nombreMenu, stringResource(R.string.alto), medidas.valorAlto)
                        }
                }
                ImageComponent {
                    selectablesPresupuestos.checkboxStateVentana.value = false
                    resetDropdown(selectablesPresupuestos.selectedTipoSerie, SERIE)
                    resetDropdown(selectablesPresupuestos.selectedColorVentana, COLOR)
                    resetDropdown(selectablesPresupuestos.selectedTipoVentana, TIPO_VENTANA)
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
                }
            }

            VIDRIO -> {
                DropDownComponent(
                    nombreMenu,
                    getItems(fireBaseViewModel)[ITEMS_VIDRIO].orEmpty(),
                    selectablesPresupuestos.selectedTipoVidrio,
                    stringResource(R.string.tipo)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }
                        .forEach { medidas ->
                            TextFieldComponent(nombreMenu, stringResource(R.string.ancho), medidas.valorAncho)
                            Spacer(modifier = Modifier.padding(10.dp))
                            TextFieldComponent(nombreMenu, stringResource(R.string.alto), medidas.valorAlto)
                        }
                }
                ImageComponent {
                    resetDropdown(selectablesPresupuestos.selectedTipoVidrio, TIPO_VIDRIO)
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
                }
            }

            PERSIANA -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropDownComponent(
                        nombreMenu,
                        getItems(fireBaseViewModel)[ITEMS_PERSIANA].orEmpty(),
                        selectablesPresupuestos.selectedTipoPersiana,
                        stringResource(R.string.tipo)
                    )
                    CheckBoxComponent(nombreMenu, selectablesPresupuestos.checkboxStatePersiana)
                    DropDownComponent(
                        nombreMenu,
                        getItems(fireBaseViewModel)[ITEMS_COLORES].orEmpty(),
                        selectablesPresupuestos.selectedColorPersiana,
                        stringResource(R.string.color)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }
                        .forEach { medidas ->
                            TextFieldComponent(nombreMenu, stringResource(R.string.ancho), medidas.valorAncho)
                            Spacer(modifier = Modifier.padding(10.dp))
                            TextFieldComponent(nombreMenu, stringResource(R.string.alto), medidas.valorAlto)

                        }
                }
                ImageComponent {
                    resetDropdown(selectablesPresupuestos.selectedTipoPersiana, TIPO_PERSIANA)
                    selectablesPresupuestos.checkboxStatePersiana.value = false
                    resetDropdown(selectablesPresupuestos.selectedColorPersiana, COLOR)
                    resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                    deleteProducto(nombreMenu)
                }
            }

            REGISTRO -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DropDownComponent(
                        nombreMenu,
                        getItems(fireBaseViewModel)[ITEMS_REGISTRO].orEmpty(),
                        selectablesPresupuestos.selectedTipoRegistro,
                        stringResource(R.string.tipo)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectablesPresupuestos.medidasState.filter { it.tipo == nombreMenu }
                        .forEach { medidas ->
                            TextFieldComponent(nombreMenu, stringResource(R.string.ancho), medidas.valorAncho)
                            Spacer(modifier = Modifier.padding(10.dp))
                            TextFieldComponent(nombreMenu, stringResource(R.string.alto), medidas.valorAlto)

                        }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ImageComponent {
                        resetDropdown(
                            selectablesPresupuestos.selectedTipoRegistro,
                            TIPO_REGISTRO
                        )
                        resetMedidas(nombreMenu, selectablesPresupuestos.medidasState)
                        deleteProducto(nombreMenu)
                    }
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
    LogicaAgregarProductos().getProductList().toMutableList().removeIf { producto -> producto.nombre == nombreMenu }
}

fun getItems(fireBaseViewModel: FireBaseViewModel): Map<String, List<String?>> {

    val itemsTipoVentana = fireBaseViewModel.producto.value
        ?.filter { it.nombre == VENTANA }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoVidrio = fireBaseViewModel.producto.value
        ?.filter { it.nombre == VIDRIO }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoPersiana = fireBaseViewModel.producto.value
        ?.filter { it.nombre == PERSIANA }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoRegistro = fireBaseViewModel.producto.value
        ?.filter { it.nombre == REGISTRO }
        ?.flatMap { it.tipo ?: emptyList() }
        ?.map { it.tipo }
        ?.toMutableList() ?: mutableListOf()

    val itemsTipoSerie = fireBaseViewModel.producto.value
        ?.asSequence()
        ?.filter { it.nombre == VENTANA }
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