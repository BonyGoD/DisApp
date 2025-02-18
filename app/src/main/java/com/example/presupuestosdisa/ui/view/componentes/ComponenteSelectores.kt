package com.example.presupuestosdisa.ui.view.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.data.model.SelectablesPresupuestos
import com.example.presupuestosdisa.ui.theme.DisaBlue
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import com.example.presupuestosdisa.utils.LogicaDropdown
import com.example.presupuestosdisa.utils.LogicaSelectores


@Composable
fun ComponenteSelectores(
    selectablesPresupuestos: SelectablesPresupuestos,
    nombreMenu: String,
    fireBaseViewModel: FireBaseViewModel
) {

    val tipoVentana = remember { mutableStateOf("") }

    LaunchedEffect(selectablesPresupuestos.selectedTipoVentana.value) {
        tipoVentana.value = selectablesPresupuestos.selectedTipoVentana.value
    }
    LogicaSelectores(
        nombreMenu,
        selectablesPresupuestos,
        tipoVentana.value,
        fireBaseViewModel
    )
}

@Composable
fun DropDownComponent(
    nombreMenu: String,
    selectores: List<String?>,
    selectedItem: MutableState<String>,
    tipoDropdown: String
) {

    val expanded = remember { mutableStateOf(false) }

    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = selectedItem.value,
            modifier = Modifier.clickable { expanded.value = true },
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 15.sp
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            selectores.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.orEmpty(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    },
                    onClick = {
                        expanded.value = false
                        selectedItem.value = item.orEmpty()
                        if(item == "Elevable" || item == "Corredera") {
                            LogicaDropdown().LogicaCheckBox(nombreMenu, "Oscilobatiente", false)
                        }
                        LogicaDropdown().LogicaDropdown2(
                            tipoDropdown,
                            nombreMenu,
                            item.orEmpty()
                        )
                    }
                )
            }
        }
        Icon(
            painter = painterResource(R.drawable.arrow_down_blue),
            contentDescription = "Arrow",
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 5.dp)
                .clickable { expanded.value = !expanded.value },
            tint = DisaBlue
        )
    }
}

@Composable
fun CheckBoxComponent(
    nombreMenu: String,
    checkedState: MutableState<Boolean>
) {

    val nombre = if (nombreMenu == "Persiana") "Motorizada" else "Oscilobatiente"

    Row() {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                LogicaDropdown().LogicaCheckBox(nombreMenu, nombre, it)
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp)
        )
        Text(
            text = nombre,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    val newCheckedState = !checkedState.value
                    checkedState.value = newCheckedState
                    LogicaDropdown().LogicaCheckBox(nombreMenu, nombre, newCheckedState)
                           },
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    nombreMenu: String,
    tipoMedida: String,
    medida: MutableState<String>
) {

    OutlinedTextField(
        value = medida.value,
        onValueChange = {
            LogicaDropdown().LogicaTextFields(nombreMenu, tipoMedida, medida, it)
        },
        label = {
            Text(
                text = tipoMedida,
                fontWeight = FontWeight.Bold
            )
        },
        singleLine = true,
        modifier = Modifier
            .padding(top = 15.dp)
            .width(130.dp)
            .height(70.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = DisaPink,
            unfocusedIndicatorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedLabelColor = DisaPink,
            unfocusedLabelColor = Color.White,
            focusedSuffixColor = Color.White,
            unfocusedSuffixColor = Color.White

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
            .padding(top = 35.dp, bottom = 15.dp)
            .clickable {
                onClick()
            }
            .size(35.dp)
    )
}
