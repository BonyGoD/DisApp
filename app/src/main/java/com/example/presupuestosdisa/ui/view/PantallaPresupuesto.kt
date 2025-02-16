package com.example.presupuestosdisa.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presupuestosdisa.R
import com.example.presupuestosdisa.data.model.SelectablesPresupuestos
import com.example.presupuestosdisa.data.model.rememberSelectablesPresupuestos
import com.example.presupuestosdisa.ui.theme.DisaPink
import com.example.presupuestosdisa.ui.theme.LowDisaPink2
import com.example.presupuestosdisa.ui.theme.LowDisaPink6
import com.example.presupuestosdisa.ui.view.componentes.ComponenteMenu
import com.example.presupuestosdisa.ui.view.componentes.ComponenteSelectores
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import com.example.presupuestosdisa.ui.viewModel.SharedViewModel

data class Productos(val nombre: String, val icono: Int)

private val productos: List<Productos> = listOf(
    Productos("Ventana", R.drawable.ventana),
    Productos("Vidrio", R.drawable.vidrio),
    Productos("Persiana", R.drawable.persiana),
    Productos("Registro", R.drawable.registro),
)

val arrowUp = R.drawable.arrow_up
val arrowDown = R.drawable.arrow_down

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPresupuesto(
    fireBaseViewModel: FireBaseViewModel,
    sharedViewModel: SharedViewModel,
    navigateBack: () -> Unit
) {

    val selectablesPresupuestos = rememberSelectablesPresupuestos()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Color.Blue),
                title = {
                    Text(
                        text = "Volver",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navigateBack()
                        },
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DisaPink
                )
            )
        }
    ) {
        ListaProductos(
            sharedViewModel,
            fireBaseViewModel,
            navigateBack,
            productos,
            selectablesPresupuestos
        )
    }
}

@Composable
fun ListaProductos(
    sharedViewModel: SharedViewModel,
    fireBaseViewModel: FireBaseViewModel,
    navigateBack: () -> Unit,
    productos: List<Productos>,
    selectablesPresupuestos: SelectablesPresupuestos
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LowDisaPink2)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 100.dp, bottom = 30.dp)
        ) {
            items(productos) { tipoProducto ->
                var expandida by remember { mutableStateOf(false) }

                ComponenteMenu(
                    tipoProducto.nombre, tipoProducto.icono, if (expandida) {
                        arrowUp
                    } else {
                        arrowDown
                    }
                ) {
                    expandida = !expandida
                }
                AnimatedVisibility(
                    expandida
                )
                {
                    ComponenteSelectores(
                        selectablesPresupuestos,
                        tipoProducto.nombre,
                        fireBaseViewModel
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    sharedViewModel.agregarListaProductos()
                    navigateBack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LowDisaPink6,  // Color desde Colors.kt
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Añadir",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
