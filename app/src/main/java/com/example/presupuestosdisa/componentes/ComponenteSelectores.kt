package com.example.presupuestosdisa.componentes

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.utils.LogicaSelectores


@Composable
fun ComponenteSelectores(
    selectedTipoVentana: MutableState<String>,
    selectedTipoVidrio: MutableState<String>,
    selectedTipoPersiana: MutableState<String>,
    selectedTipoRegistro: MutableState<String>,
    selectedTipoSerie: MutableState<String>,
    selectedTipoColor: MutableState<String>,
    nombreMenu: String
) {

    val tipoVentana = remember { mutableStateOf("") }

    LaunchedEffect(selectedTipoVentana.value) {
        // Aquí puedes tratar los datos cuando selectedTipoVentana cambie
        tipoVentana.value = selectedTipoVentana.value
        // R
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LogicaSelectores(
            nombreMenu,
            selectedTipoVentana,
            selectedTipoVidrio,
            selectedTipoPersiana,
            selectedTipoRegistro,
            selectedTipoSerie,
            selectedTipoColor,
            tipoVentana.value
        )
    }
}

@Composable
fun DropDown(items: List<String>, selectedItem: MutableState<String>) {
    val expanded = remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(10.dp)) {
        Text(
            text = selectedItem.value,
            modifier = Modifier.clickable { expanded.value = true }
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        expanded.value = false
                        selectedItem.value = item
                        // Handle item click
                    }
                )
            }
        }
    }
}

@Composable
fun CheckBox(nombreMenu: String) {

    val checkedState = remember { mutableStateOf(false) }
    val nombre = if (nombreMenu == "Persiana") "Motorizada" else "Oscilobatiente"

    Row() {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier.align(Alignment.CenterVertically).size(30.dp)
        )
        Text(
            text = nombre,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun ComponenteSeleccionableVentanaPreview() {
    ComponenteSelectores(
        selectedTipoVentana = mutableStateOf("Tipo de ventana"),
        selectedTipoVidrio = mutableStateOf("Tipo de vidrio"),
        selectedTipoPersiana = mutableStateOf("Tipo de persiana"),
        selectedTipoRegistro = mutableStateOf("Tipo de registro"),
        selectedTipoSerie = mutableStateOf("Tipo de serie"),
        selectedTipoColor = mutableStateOf("Color"),
        nombreMenu = "Vidrio")
}