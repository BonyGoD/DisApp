package com.devware.disapp.data.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class SelectablesPresupuestos(
    val selectedTipoVentana: MutableState<String>,
    val selectedTipoVidrio: MutableState<String>,
    val selectedTipoPersiana: MutableState<String>,
    val selectedTipoRegistro: MutableState<String>,
    val selectedTipoSerie: MutableState<String>,
    val selectedColorVentana: MutableState<String>,
    val selectedColorPersiana: MutableState<String>,
    val checkboxStateVentana: MutableState<Boolean>,
    val checkboxStatePersiana: MutableState<Boolean>,
    val medidasState: MutableList<MedidasState>
)

@Composable
fun rememberSelectablesPresupuestos(): SelectablesPresupuestos {
    return SelectablesPresupuestos(
        selectedTipoVentana = remember { mutableStateOf("Tipo de Ventana") },
        selectedTipoVidrio = remember { mutableStateOf("Tipo de Vidrio") },
        selectedTipoPersiana = remember { mutableStateOf("Tipo de Persiana") },
        selectedTipoRegistro = remember { mutableStateOf("Tipo de Registro") },
        selectedTipoSerie = remember { mutableStateOf("Serie") },
        selectedColorVentana = remember { mutableStateOf("Color") },
        selectedColorPersiana = remember { mutableStateOf("Color") },
        checkboxStateVentana = remember { mutableStateOf(false) },
        checkboxStatePersiana = remember { mutableStateOf(false) },
        medidasState = remember {
            mutableListOf(
                MedidasState("Ventana"),
                MedidasState("Vidrio"),
                MedidasState("Persiana"),
                MedidasState("Registro")
            )
        }
    )
}