package com.example.presupuestosdisa.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.theme.ButtonDisaColor
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.ui.view.componentes.ComponenteVersionControl
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import com.example.presupuestosdisa.ui.viewModel.SharedViewModel
import com.example.presupuestosdisa.utils.calcularPrecioTotal

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PantallaPrincipal(fireBaseViewModel: FireBaseViewModel, sharedViewModel: SharedViewModel, navigateToPantallaPresupuesto: () -> Unit) {

    val productos by sharedViewModel.productos.collectAsState()

    ComponenteVersionControl()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DisaPink),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imagenDisa()
        Column {
            productos.forEach { producto ->
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = "${producto.nombre} - " +
                            "${producto.tipo} - " +
                            (if(producto.oscilobatiente) "Oscilobatiente - " else "") +
                            (if(producto.motorizada) "Motorizada - " else "") +
                            (if(producto.tipoSerie != "") producto.tipoSerie + " - " else "") +
                            if(producto.tipoColor != "") producto.tipoColor else ""
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if (sharedViewModel.productos.value.isNotEmpty()) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                text = "${producto.ancho} x ${producto.alto}",
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                text = calcularPrecioTotal(null, producto, fireBaseViewModel),
                                color = Color.White
                            )
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.duplicar_principal),
                                    contentDescription = "Duplicar",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(10.dp)
                                        .clickable {
                                            sharedViewModel.productos.value.find { it == producto }
                                                ?.let {
                                                    sharedViewModel.agregarListaProductos(listOf(it))
                                                }
                                        }
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.basura_principal),
                                    contentDescription = "Basura",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(10.dp)
                                        .clickable {
                                            sharedViewModel.productos.value.find { it == producto }
                                                ?.let {
                                                    sharedViewModel.eliminarProducto(it)
                                                }
                                        }
                                )
                            }
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (sharedViewModel.productos.value.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        text = "TOTAL:",
                        color = Color.White)
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        text = calcularPrecioTotal(sharedViewModel.productos.value, null, fireBaseViewModel),
                        color = Color.White
                    )
                }
            }
        }
        Button(onClick = {
            navigateToPantallaPresupuesto()
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonDisaColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "+ Añadir presupuesto", fontSize = 20.sp)
        }
    }
}

@Composable
fun imagenDisa() {
    Image(
        painterResource(R.drawable.logodisa),
        contentDescription = "Logo de Disa",
        modifier = Modifier
            .padding(bottom = 25.dp)
            .size(200.dp)
            .clip(RoundedCornerShape(50.dp))
    )
}