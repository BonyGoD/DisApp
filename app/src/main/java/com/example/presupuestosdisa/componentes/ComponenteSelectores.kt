package com.example.presupuestosdisa.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.utils.LogicaSelectores
import com.example.presupuestosdisa.utils.MedidasState


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
    medidasState: List<MedidasState>,
    nombreMenu: String
) {

    val tipoVentana = remember { mutableStateOf("") }

    LaunchedEffect(selectedTipoVentana.value) {
        tipoVentana.value = selectedTipoVentana.value
    }
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
        medidasState,
        tipoVentana.value
    )
}

@Composable
fun DropDownComponent(items: List<String>, selectedItem: MutableState<String>) {
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
fun CheckBoxComponent(nombreMenu: String, checkedState: MutableState<Boolean>) {

    val nombre = if (nombreMenu == "Persiana") "Motorizada" else "Oscilobatiente"

    Row() {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp)
        )
        Text(
            text = nombre,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(tipoMedida: String, medida: MutableState<String>) {
    val maxDigits = 5

    TextField(
        value = medida.value,
        onValueChange = {
            if (it.length <= maxDigits && it.all { char -> char.isDigit() }) {
                medida.value = it
            }
        },
        label = { Text(tipoMedida) },
        modifier = Modifier.width(130.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        suffix = {
            Text("mm")
        }
    )
}

@Composable
fun ImageComponent(onClick: () -> Unit) {
    Image(
        painter = painterResource(R.drawable.basura),
        contentDescription = "Basura",
        modifier = Modifier
            .clickable {
                onClick()
            }
            .size(20.dp)
    )
}
