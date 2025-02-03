package com.example.presupuestosdisa.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.viewModel.SharedViewModel

@Composable
fun PantallaPrincipal(sharedViewModel: SharedViewModel, navigateToPantallaPresupuesto: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DisaPink),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imagenDisa()
        Column {
            sharedViewModel.productos.value.forEach { producto ->
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    text = "${producto.nombre} - " +
                            "${producto.tipo} - " +
                            "${if(producto.oscilobatiente) "Oscilobatiente - " else ""}" +
                            "${if(producto.motorizada) "Motorizada - " else ""}" +
                            "${if(producto.tipoSerie != "") producto.tipoSerie + " - " else ""}" +
                            "${if(producto.tipoColor != "") producto.tipoColor + " - " else ""}"
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    text = "${producto.ancho} x ${producto.alto}"
                )
            }
        }
        Button(onClick = {
            navigateToPantallaPresupuesto()
        }) {
            Text(text = "+ Añadir presupuesto")
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