package com.example.presupuestosdisa.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponenteMedidas(nombreMenu: String? = null, tipoVentana: String? = null) {

    val textoAlto = remember { mutableStateOf("") }
    val textoAncho = remember { mutableStateOf("") }
    val maxDigits = 5

    fun resetTextFields() {
        textoAlto.value = ""
        textoAncho.value = ""
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Medidas",
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = textoAncho.value,
            onValueChange = {
                if (it.length <= maxDigits && it.all { char -> char.isDigit() }) {
                    textoAncho.value = it
                }
            },
            label = { Text("Ancho") },
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
                Row {
                    Text("mm")
                }
            }
        )
        TextField(
            value = textoAlto.value,
            onValueChange = {
                if (it.length <= maxDigits && it.all { char -> char.isDigit() }) {
                    textoAlto.value = it
                }
            },
            label = { Text("Alto") },
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
                Row {
                    Text("mm")
                }
            }
        )
    }
    ImageComponent(onClick = { resetTextFields() })
}