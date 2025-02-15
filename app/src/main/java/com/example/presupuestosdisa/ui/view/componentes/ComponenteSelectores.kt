package com.example.presupuestosdisa.ui.view.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.data.model.Producto
import com.example.presupuestosdisa.data.model.SelectablesPresupuestos
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

    val arrowDown = R.drawable.arrow_down
    val expanded = remember { mutableStateOf(false) }

    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = selectedItem.value,
            modifier = Modifier.clickable { expanded.value = true },
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            selectores.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.orEmpty(), fontWeight = FontWeight.Bold, fontSize = 15.sp) },
                    onClick = {
                        expanded.value = false
                        selectedItem.value = item.orEmpty()
                        when (tipoDropdown) {
                            "Tipo" -> LogicaDropdown().LogicaDropdown2(
                                tipoDropdown,
                                nombreMenu,
                                item.orEmpty()
                            )

                            "Serie" -> LogicaDropdown().LogicaDropdown2(
                                tipoDropdown,
                                nombreMenu,
                                item.orEmpty()
                            )

                            "Color" -> LogicaDropdown().LogicaDropdown2(
                                tipoDropdown,
                                nombreMenu,
                                item.orEmpty()
                            )
                        }
                    }
                )
            }
        }
        Image(
            painter = painterResource(arrowDown),
            contentDescription = "Arrow",
            modifier = Modifier.size(20.dp).align(Alignment.CenterVertically).padding(start = 5.dp).clickable { expanded.value = !expanded.value }
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
                var productoEncontrado = false
                LogicaDropdown().getProductList().forEach { producto ->
                    if (producto.nombre == nombreMenu) {
                        when (nombre) {
                            "Motorizada" -> producto.motorizada = it
                            "Oscilobatiente" -> producto.oscilobatiente = it
                        }
                        productoEncontrado = true
                        return@forEach
                    }
                }
                if (!productoEncontrado) {
                    LogicaDropdown().getProductList().add(
                        Producto(
                            nombre = nombreMenu,
                            motorizada = if (nombre == "Motorizada") it else false,
                            oscilobatiente = if (nombre == "Oscilobatiente") it else false

                        )
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp),
        )
        Text(
            text = nombre,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { checkedState.value = !checkedState.value },
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
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
    val maxDigits = 5

    TextField(
        value = medida.value,
        onValueChange = {
            if (it.length <= maxDigits && it.all { char -> char.isDigit() }) {
                medida.value = it
                var productoEncontrado = false
                LogicaDropdown().getProductList().forEach { producto ->
                    if (producto.nombre == nombreMenu) {
                        when (tipoMedida) {
                            "Ancho" -> producto.ancho = if (it.isNotEmpty()) it.toLong() else 0L
                            "Alto" -> producto.alto = if (it.isNotEmpty()) it.toLong() else 0L
                        }
                        productoEncontrado = true
                        return@forEach
                    }
                }
                if (!productoEncontrado) {
                    LogicaDropdown().getProductList().add(
                        Producto(
                            nombre = nombreMenu,
                            ancho = if (tipoMedida == "Ancho") it.toLong() else 0,
                            alto = if (tipoMedida == "Alto") it.toLong() else 0
                        )
                    )
                }
            }
        },
        label = { Text(text = tipoMedida, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center) },
        modifier = Modifier
            .padding(top = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, DisaPink, RoundedCornerShape(10.dp))
            .width(120.dp)
            .height(50.dp),
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
            .padding(top = 15.dp)
            .clickable {
                onClick()
            }
            .size(20.dp)
    )
}
