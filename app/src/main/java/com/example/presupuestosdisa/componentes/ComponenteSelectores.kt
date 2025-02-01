package com.example.presupuestosdisa.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.model.Ventana
import com.example.presupuestosdisa.utils.LogicaSelectores


@Composable
fun ComponenteSelectores(
    selectedTipoVentana: MutableState<String>,
    selectedTipoVidrio: MutableState<String>,
    selectedTipoPersiana: MutableState<String>,
    selectedTipoRegistro: MutableState<String>,
    selectedTipoSerie: MutableState<String>,
    selectedTipoColorVentana: MutableState<String>,
    selectedTipoColorPersiana: MutableState<String>,
    checkboxStateVentana: MutableState<Boolean>,
    checkBoxStatePersiana: MutableState<Boolean>,
    nombreMenu: String
) {

    val tipoVentana = remember { mutableStateOf("") }

    LaunchedEffect(selectedTipoVentana.value) {
        tipoVentana.value = selectedTipoVentana.value
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LogicaSelectores(
            nombreMenu,
            selectedTipoVentana,
            selectedTipoVidrio,
            selectedTipoPersiana,
            selectedTipoRegistro,
            selectedTipoSerie,
            selectedTipoColorVentana,
            selectedTipoColorPersiana,
            checkboxStateVentana,
            checkBoxStatePersiana,
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
fun CheckBox(nombreMenu: String, checkedState: MutableState<Boolean>) {

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
